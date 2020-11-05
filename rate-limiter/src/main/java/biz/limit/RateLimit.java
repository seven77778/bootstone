package biz.limit;

/**
 * Created by lsh on 2020-09-23.
 *
 *  限流降级
 *
 *  升级理念
 *   // >100us, use mutex lock
 *     // >15us, use yield, just context switch
 *     // <15us, use spin
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
