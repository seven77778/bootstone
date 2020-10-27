package biz.limit;

/**
 * Created by lsh on 2020-09-23.
 *
 *  限流降级
 */
public interface RateLimit {

    String getType();

    void limit(RateConfig config);


    /**
     * 释放
     * @param config
     */
    void releaseLimit(RateConfig config);

    void init();

}
