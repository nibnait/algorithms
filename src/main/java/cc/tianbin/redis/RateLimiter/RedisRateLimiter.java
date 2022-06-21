package cc.tianbin.redis.RateLimiter;

import cc.tianbin.java.IO.classpath.ResourcesReaderUtil;
import com.google.common.io.ByteStreams;
import io.github.nibnait.common.constants.CommonConstants;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nibnait on 2020/11/17
 */
public class RedisRateLimiter {

    // 当前存储的令牌数
    private static final String STORED_PERMITS = "storedPermits";
    // 最大可存储的令牌数
    private static final String MAX_PERMITS = "maxPermits";
    // 多久产生一个令牌
    private static final String STABLE_INTERVAL_MICROS = "stableIntervalMicros";
    // 下一次可以获取令牌的时间点
    private static final String NEXT_FREE_TICKET_MICROS = "nextFreeTicketMicros";

    private static final String LUA_SCRIPT_FILE_PATH = "/redis/lua/RedLimiter.lua";
    private static final String SCRIPT = readScript();

    private static final ConcurrentMap<String, RedisRateLimiter> LIMITERS = new ConcurrentHashMap<>();

    private final String key;
    private final JedisPool jedisPool;
    private final JedisCluster jedisCluster;
    private double qps;
    private String sha1;
    private volatile int batchSize = 100;
    private volatile long lastMillis = 0L;
    private volatile long batchInterval = 100L;

    private AtomicInteger qpsHolder = new AtomicInteger(0);

    private RedisRateLimiter(String key, double qps, JedisPool jedisPool, JedisCluster jedisCluster, boolean setProperties) {
        this.key = key;
        this.qps = qps;
        this.jedisPool = jedisPool;
        this.jedisCluster = jedisCluster;
        if (jedisPool == null && jedisCluster == null) {
            throw new RuntimeException("no redis client");
        }
        if (setProperties) {
            setProperties();
        }
        loadScriptSha1();
    }

    private void setProperties() {
        Map<String, String> limiter = new HashMap<>();
        limiter.put(STORED_PERMITS, Double.toString(qps));
        limiter.put(MAX_PERMITS, Double.toString(qps));
        limiter.put(STABLE_INTERVAL_MICROS, Double.toString(TimeUnit.SECONDS.toMicros(1L) / qps));
        limiter.put(NEXT_FREE_TICKET_MICROS, "0");

        if (jedisPool != null) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.hmset(key, limiter);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                jedisCluster.hmset(key, limiter);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadScriptSha1() {
        if (jedisPool != null) {
            try (Jedis jedis = jedisPool.getResource()) {
                this.sha1 = jedis.scriptLoad(SCRIPT);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                this.sha1 = jedisCluster.scriptLoad(SCRIPT, key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String readScript() {
        try {
            InputStream inputStream = new ResourcesReaderUtil().getResource(LUA_SCRIPT_FILE_PATH);
            return new String(ByteStreams.toByteArray(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return CommonConstants.EMPTY_STRING;
    }

    public static RedisRateLimiter create(String key, double qps, JedisPool jedisPool) {
        return create(key, qps, jedisPool, false);
    }

    public static RedisRateLimiter create(String key, double qps, JedisPool jedisPool, boolean setProperties) {
        return LIMITERS.computeIfAbsent(key, k -> new RedisRateLimiter(k, qps, jedisPool, null, setProperties));
    }

    public static RedisRateLimiter create(String key, double qps, JedisCluster jedisCluster) {
        return create(key, qps, jedisCluster, false);
    }

    public static RedisRateLimiter create(String key, double qps, JedisCluster jedisCluster, boolean setProperties) {
        return LIMITERS.computeIfAbsent(key, k -> new RedisRateLimiter(k, qps, null, jedisCluster, setProperties));
    }

    public void setRate(double qps) {
        this.qps = qps;
        setProperties();
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public void setBatchInterval(long batchInterval) {
        this.batchInterval = batchInterval;
    }

    public double acquire() {
        return acquire(1D);
    }

    public double acquireLazy(int batchQps) {
        qpsHolder.addAndGet(batchQps);
        long currentMillis = System.currentTimeMillis();
        if (qpsHolder.get() >= batchSize || (currentMillis - this.lastMillis) >= batchInterval) {
            int qps = qpsHolder.getAndSet(0);
            this.lastMillis = currentMillis;
            return acquire(qps);
        } else {
            return 0D;
        }
    }

    public double acquire(double qps) {
        long nowMicros = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
        long waitMicros;

        if (jedisPool != null) {
            try (Jedis jedis = jedisPool.getResource()) {
                waitMicros = (long) jedis.evalsha(sha1, 1, key, "acquire",
                        Double.toString(qps), Long.toString(nowMicros));
            }
        } else {
            waitMicros = (long) jedisCluster.evalsha(sha1, 1, key, "acquire",
                    Double.toString(qps), Long.toString(nowMicros));
        }

        double wait = 1.0 * waitMicros / TimeUnit.SECONDS.toMicros(1L);

        if (waitMicros > 0) {
            sleepUninterruptibly(waitMicros, TimeUnit.MICROSECONDS);
        }

        return wait;
    }

    public boolean tryAcquire() {
        return tryAcquire(1D, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(long timeout, TimeUnit unit) {
        return tryAcquire(1D, timeout, unit);
    }

    public boolean tryAcquire(double qps, long timeout, TimeUnit unit) {
        long nowMicros = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
        long timeoutMicros = unit.toMicros(timeout);
        long waitMicros;

        if (jedisPool != null) {
            try (Jedis jedis = jedisPool.getResource()) {
                waitMicros = (long) jedis.evalsha(sha1, 1, key, "tryAcquire",
                        Double.toString(qps), Long.toString(nowMicros), Long.toString(timeoutMicros));
            }
        } else {
            waitMicros = (long) jedisCluster.evalsha(sha1, 1, key, "tryAcquire",
                    Double.toString(qps), Long.toString(nowMicros), Long.toString(timeoutMicros));
        }

        if (waitMicros < 0) {
            return false;
        }

        if (waitMicros > 0) {
            sleepUninterruptibly(waitMicros, TimeUnit.MICROSECONDS);
        }

        return true;
    }

    // from Guava Uninterruptibles
    private static void sleepUninterruptibly(long sleepFor, TimeUnit unit) {
        boolean interrupted = false;

        try {
            long remainingNanos = unit.toNanos(sleepFor);
            long end = System.nanoTime() + remainingNanos;

            while (true) {
                try {
                    // TimeUnit.sleep() treats negative timeouts just like zero.
                    TimeUnit.NANOSECONDS.sleep(remainingNanos);
                    return;
                } catch (InterruptedException e) {
                    interrupted = true;
                    remainingNanos = end - System.nanoTime();
                }
            }

        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
