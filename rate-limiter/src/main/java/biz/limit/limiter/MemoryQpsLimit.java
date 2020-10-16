package biz.limit.limiter;

import biz.limit.RateConfig;
import biz.limit.RateLimit;

public class MemoryQpsLimit implements RateLimit {

    @Override
    public String getType() {
        return "memory-qps";
    }

    @Override
    public void limit(RateConfig config) {

    }

    @Override
    public void releaseLimit(RateConfig config) {

    }
}
