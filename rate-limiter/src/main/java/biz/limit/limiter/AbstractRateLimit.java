package biz.limit.limiter;

import biz.limit.RateConfig;
import biz.limit.RateLimit;
import biz.limit.controller.AbstractConfiger;

public class AbstractRateLimit extends AbstractConfiger implements RateLimit {


    @Override
    protected void afterAdd() {

    }

    @Override
    protected void afterDelete() {

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void limit(RateConfig config) {
    }

    @Override
    public void releaseLimit(RateConfig config) {

    }
}
