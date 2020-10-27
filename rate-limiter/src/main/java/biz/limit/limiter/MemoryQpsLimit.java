package biz.limit.limiter;

import biz.limit.RateConfig;
import biz.limit.RateLimit;
import biz.limit.exception.RateLimitException;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import java.util.Map;

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
