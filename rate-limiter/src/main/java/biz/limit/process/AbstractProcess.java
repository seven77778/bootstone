package biz.limit.process;

import biz.limit.RateConfig;
import biz.limit.RateLimit;
import com.google.common.collect.Maps;
import com.lsh.demo.bootstone.service.common.BootStoneLog;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;

public abstract class AbstractProcess implements InitializingBean {

    private List<RateLimit> limiters = Lists.newArrayList();

    private static final Map<String, RateLimit> limiterMap = Maps.newConcurrentMap();

    protected final void releaseLimit(RateConfig config) {
        try {
            if (config == null) {
                return;
            }
            RateLimit currentLimiter = getLimiterByConfig(config);
            if (currentLimiter == null) {
                return;
            }
            currentLimiter.releaseLimit(config);
        } catch (Exception e) {
            BootStoneLog.bootStone.error("releaseLimit error" + e.toString());
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        for (RateLimit limiter : limiters) {
            limiterMap.put(limiter.getType(), limiter);
        }
    }


    private  RateLimit getLimiterByConfig(RateConfig config) {
        if (config == null) {
            return null;
        }
        String key =  config.getType();
        return limiterMap.get(key);
    }

}
