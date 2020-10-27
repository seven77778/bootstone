package biz.limit.limiter;


import biz.limit.RateConfig;
import biz.limit.RateLimit;
import biz.limit.exception.RateLimitException;
import com.google.common.collect.Maps;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

@Component
public class MemoryCurrentNumLimit implements RateLimit {

    /**
     * Semaphore 计数器信号量
     */
    private final Map<String, Semaphore> semaphores = Maps.newConcurrentMap();

    private List<RateConfig> rateConfigs;

    /**
     * 记录释放信号量
     */
    private final Map<String, Boolean> history = Maps.newConcurrentMap();


    @Override
    public String getType() {
        return "memory-num";
    }

    @Override
    public void limit(RateConfig config) {
        Semaphore semaphore = semaphores.get(config.getName());
        if (null == semaphore) {
            semaphores.put(config.getName(), new Semaphore(config.getLimit()));
        } else {

            if(config.getWait()){
                //tryAcquire 非阻塞
                boolean success = semaphore.tryAcquire();
                if (success) {
                    System.out.println("当前剩余可用信号量：" + semaphore.availablePermits());
                    history.put(getHistoryKey(config), true);
                } else {
                    throw new RateLimitException();
                }
            }else {
                try {
                    // Acquire阻塞
                    semaphore.acquire();
                    System.out.println("当前剩余可用信号量：" + semaphore.availablePermits());
                    history.put(getHistoryKey(config), true);
                } catch (InterruptedException e) {
                    throw new RateLimitException();
                }
            }

        }
    }

    @Override
    public void releaseLimit(RateConfig config) {
        String historyKey = getHistoryKey(config);
        if (history.get(historyKey) == null) {
            BootStoneLog.bootStone.info(historyKey + " already release");
        } else {
            Semaphore semaphore = semaphores.get(config.getName());
            if (null != semaphore) {
                semaphore.release();
                history.remove(historyKey);
            }
            BootStoneLog.bootStone.info(historyKey + " release success");
        }
    }

    @Override
    public void init() {

    }

    private String getHistoryKey(RateConfig config) {
        return config.getName() + "-" + Thread.currentThread().getId();
    }


}
