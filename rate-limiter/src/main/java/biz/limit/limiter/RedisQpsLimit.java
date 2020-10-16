package biz.limit.limiter;

import biz.limit.RateConfig;
import biz.limit.RateLimit;
import com.lsh.demo.bootstone.service.util.RedisUtil;

import javax.annotation.Resource;

public class RedisQpsLimit implements RateLimit {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public String getType() {
        return "redis-qps";
    }

    @Override
    public void limit(RateConfig config) {



    }

    @Override
    public void releaseLimit(RateConfig config) {

    }


}
