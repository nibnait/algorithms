package cc.tianbin.demo.redis.RateLimiter;

/**
 * Created by nibnait on 2020/11/17
 */
public class RedisRateLimiterTest {
//    private static RedisRateLimiter limiter;
//
//    @BeforeClass
//    public static void init() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(200);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost");
//        limiter = RedisRateLimiter.create("1000", 1, jedisPool,true);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        Thread.sleep(2000L);
//    }
//
//    private ExecutorService pool = Executors.newFixedThreadPool(500);
//
//    @Test
//    public void acquire() throws Exception {
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//
//            pool.execute(() -> {
//                double acquire = limiter.acquire(1);
//                System.out.println(index + " \t" + acquire + " \t"
//                        + DateTimeConvertUtils.localDateTime2String(LocalDateTime.now()));
//            });
//        }
//
//        Thread.sleep(5 * 1000L);
//    }
//
//    @Test
//    public void tryAcquire() throws Exception {
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//
//            pool.execute(() -> {
//                boolean acquire = limiter.tryAcquire();
//                System.out.println(index + " \t" + acquire + " \t"
//                        + DateTimeConvertUtils.localDateTime2String(LocalDateTime.now()));
//            });
//        }
//
//        Thread.sleep(5 * 1000L);
//    }
//
//    @Test
//    public void tryAcquireTimeout() throws Exception {
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//
//            pool.execute(() -> {
//                boolean acquire = limiter.tryAcquire(1000L, TimeUnit.MILLISECONDS);
//                System.out.println(index + " \t" + acquire + " \t"
//                        + DateTimeConvertUtils.localDateTime2String(LocalDateTime.now()));
//            });
//        }
//
//        Thread.sleep(5 * 1000L);
//    }
//
//    @Test
//    public void batchAcquireLazy() throws Exception {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(50);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost");
//
//        RedisRateLimiter redisRateLimiter = RedisRateLimiter.create("500", 1000, jedisPool, true);
//        redisRateLimiter.setBatchSize(50);
//
//        for (int i = 0; i < 1000; i++) {
//            final String index = String.format("%3d", i);
//
//            pool.execute(() -> {
//                double acquire = redisRateLimiter.acquireLazy(10);
//                System.out.println(index + " \t" + acquire + " \t"
//                        + DateTimeConvertUtils.localDateTime2String(LocalDateTime.now()));
//            });
//        }
//
//        Thread.sleep(5 * 1000L);
//    }
//
}
