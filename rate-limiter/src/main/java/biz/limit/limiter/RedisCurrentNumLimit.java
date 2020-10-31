package biz.limit.limiter;

import biz.limit.RateConfig;
import biz.limit.RateLimit;
import biz.limit.exception.RateLimitException;
import com.google.common.collect.Maps;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis 并发量
 * 可过期信号量
 */
@Component
public class RedisCurrentNumLimit implements RateLimit {

    private final Map<String, RPermitExpirableSemaphore> semphores = Maps.newConcurrentMap();

    /**
     * permitId统一存储
     */
    private Map<String, String> permitIds = Maps.newConcurrentMap();

    @Resource
    @Lazy
    private RedissonClient redissonClient;

    @Override
    public String getType() {
        return "redis-num";
    }

    @Override
    public void limit(RateConfig config) {
        RPermitExpirableSemaphore semaphore = semphores.get(config.getName());
        if (null == semaphore) {
            semaphore = redissonClient.getPermitExpirableSemaphore(config.getName());
            while (!semaphore.trySetPermits(config.getLimit())) {
            }
            semphores.put(config.getName(), semaphore);
        } else {
            try {
                String per = semaphore.tryAcquire(1, 1, TimeUnit.SECONDS);
                if (StringUtils.isNotBlank(per)) {
                    permitIds.put(config.getName(), per);
                    BootStoneLog.bootStone.info("获取信号量成功" + "-" + semaphore.availablePermits());
                } else {
                    throw new RateLimitException("redisson");
                }
            } catch (InterruptedException e) {
                BootStoneLog.bootStone.error("redisson error " + e.toString());
            }
        }


    }

    @Override
    public void releaseLimit(RateConfig config) {
        String permit = permitIds.get(config.getName());
        RPermitExpirableSemaphore semaphore = semphores.get(config.getName());
        if(null!=semaphore && StringUtils.isNotBlank(permit)){
            semaphore.release(permit);
            permitIds.remove(permit);
            BootStoneLog.bootStone.info("释放redisson成功");

        }

    }

    @Override
    public void init() {
        BootStoneLog.bootStone.info(getType() + "start init......");
        //todo

    }
}
