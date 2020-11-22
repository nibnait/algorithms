# RedisRateLimiter
RedisRateLimiter，与Guava RateLimiter功能类似，基于Redis+Lua+令牌桶算法实现的分布式限流器，提供
- 限流
- 流量整形
- 批处理避免Redis热点

## 原理
算法基于令牌桶算法，一种方式是一个线程匀速不断向桶中添加令牌，显而易见这种方式很好资源；另一种则是延迟计算——即在获取时才计算需要加多少令牌，本次需要等多久，如何更新数据等等，这个好处是不需要一个添加令牌的任务，只需要在获取令牌时进行一次计算即可。

这里采用第二种方式，由于涉及到多个计算步骤，为了保证整个过程的原子性，就要依靠redis单线程以及可以支持lua脚本执行这个特性了。话不多说，整个lua脚本内容如下（也就是整个获取令牌过程）。

限流器的几个关键属性

storedPermits 当前存储的令牌数
maxPermits 最大可存储的令牌数，设置为限速器的qps
stableIntervalMicros 多久产生一个令牌
nextFreeTicketMicros 下一次可以获取令牌的时间点

- 令牌桶算法
- 通过延迟计算方式向令牌桶里加入令牌
- 通过Lua脚本和Redis单线程的特性保证操作的原子性


参数 [https://github.com/diracccc/redis-rate-limiter](https://github.com/diracccc/redis-rate-limiter)