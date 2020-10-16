package biz.limit.limiter;

import biz.limit.RateConfig;
import biz.limit.RateLimit;
import biz.limit.exception.RateLimitException;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import com.lsh.demo.bootstone.service.util.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis 并发量
 */
@Component
public class RedisCurrentNumLimit implements RateLimit {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public String getType() {
        return "redis-num";
    }

    @Override
    public void limit(RateConfig config) {
        Long result = redisUtil.incr(config.getName());
        if (result <= config.getLimit()) {
            BootStoneLog.bootStone.info("limit success,current is " + result);
        } else {
            throw new RateLimitException("redis-num");
        }
    }

    @Override
    public void releaseLimit(RateConfig config) {
        Long result = redisUtil.decrease(config.getName());
        BootStoneLog.bootStone.info("release success,current is " + result);
    }
}
