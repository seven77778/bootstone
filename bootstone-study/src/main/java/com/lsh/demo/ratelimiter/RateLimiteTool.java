package com.lsh.demo.ratelimiter;

/**
 * Created by lsh on 2020-04-13.
 */
public class RateLimiteTool {

    /**
     *
     * 1.redis qps 控制每秒多少量就是QPS
     * only redis，incr方法
     * key是每秒生成一个新的  key = id + current_Second;
     * long limit = jedis.incr(key);
     * 如果 limit ==1 代表 是第一次请求，给key设置一个失效时间（最好使用lua脚本）
     *
     *
     * 2.redis 并发数
     * redission + RPermitExpirableSemaphore 来实现
     * RPermitExpirableSemaphore 可过期性信号量RPermitExpirableSemaphore是在RSemaphore对象的基础上，
     * 为每个信号增加了一个过期时间
     *
     *
     *
     */

}
