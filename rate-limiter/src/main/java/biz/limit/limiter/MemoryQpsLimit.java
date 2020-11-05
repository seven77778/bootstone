package biz.limit.limiter;

import biz.limit.RateConfig;
import biz.limit.RateLimit;
import biz.limit.exception.RateLimitException;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 *RateLimiter 和 Semaphore 都是单机限流，是基于jvm的限流
 * 分布式限流使用redis
 *
 * 令牌桶算法
 * 就好像包子店，每隔一秒 拿出两个包子（放到桌面上，需求的人自己拿）来卖，顾客买了就走
 *
 * 1.最大考验存储多少个令牌？
 * todo
 *
 * 2.允许突发，突发会不会搞垮系统？
 * 【SmoothBursty 允许一定程度的突发，会有人担心如果允许这种突发，假设突然间来了很大的流量，那么系统很可能扛不住这种突发。
 * 因此需要一种平滑速率的限流工具，从而系统冷启动后慢慢的趋于平均固定速率（即刚开始速率小一些，然后慢慢趋于我们设置的固定速率）。
 * Guava 也提供了 SmoothWarmingUp 来实现这种需求，一开始的速率较慢，然后慢慢提高到期望值。】
 *
 * 优点：相比漏桶算法，令牌桶算法允许一定的突发流量，但是又不会让突发流量超过我们给定的限制（单位时间窗口内的令牌数）。即限制了我们所说的 QPS。
 *
 *
 * 漏桶算法：
 * 来一个人，拿走一个包子
 * 缺点：瓶颈会在漏出的速度，可能会拖慢整个系统，且不能有效地利用系统的资源。
 * 为什么不给定一个相对合适的速度？
 *
 * @see com.lsh.demo.ratelimiter.RateLimiterLearn
 */
@Component
public class MemoryQpsLimit implements RateLimit {

    private final Map<String, RateLimiter> tokens = Maps.newConcurrentMap();





    @Override
    public String getType() {
        return "memory-qps";
    }

    @Override
    public void limit(RateConfig config) {

        RateLimiter rateLimit = tokens.get(config.getName());
        if (rateLimit == null) {
            RateLimiter limiter = RateLimiter.create(config.getLimit());
            tokens.put(config.getName(), limiter);
        } else if (rateLimit != null && !rateLimit.tryAcquire(1)) {
            throw new RateLimitException("memory-qps");
        }
    }

    @Override
    public void releaseLimit(RateConfig config) {
        System.out.println("memory-qps release");

    }

    @Override
    public void init() {

    }
}
