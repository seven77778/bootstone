package com.lsh.demo.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by lsh on 2019-09-03.
 *
 * 单机版：RateLimiter，Semaphore
 * 集群版：RedissonClient，redis
 *
 * see https://www.jianshu.com/p/5d4fe4b2a726
 *
 * 漏桶算法：
 * 漏桶算法思路很简单，水（请求）先进入到漏桶里，漏桶以一定的速度出水，
 * 当水流入速度过大会直接溢出，可以看出漏桶算法能强行限制数据的传输速率
 *
 * 对于很多应用场景来说，除了要求能够限制数据的平均传输速率外，还要求允许某种程度的突发传输。
 * 这时候漏桶算法可能就不合适了，令牌桶算法更为适合。
 *
 *
 * 令牌桶算法：
 * 令牌桶算法的原理是系统会以一个恒定的速度往桶里放入令牌，
 * 而如果请求需要被处理，则需要先从桶里获取一个令牌，
 * 当桶里没有令牌可取时，则拒绝服务
 *
 */
public class RateLimiterLearn {


    /**
     * 首先通过RateLimiter.create(1);
     * 创建一个限流器，参数代表每秒生成的令牌数，通过limiter.acquire(i);来以【阻塞】的方式获取令牌
     *
     * 当然也可以通过tryAcquire(int permits, long timeout, TimeUnit unit)来设置等待超时时间的方式获取令牌
     * 如果超timeout为0，则代表非阻塞，获取不到立即返回
     *
     * 从输出来看，RateLimiter支持预消费，比如在acquire(5)时，等待时间是3秒，是上一个获取令牌时预消费了3个令牌，
     * 固需要等待3*1秒，然后又预消费了5个令牌，以此类推
     *
     * RateLimiter通过限制后面请求的等待时间，来支持一定程度的突发请求(预消费)，在使用过程中需要注意这一点，具体实现原理后面再分析。
     */
    @Test
    public void test() throws Exception{
        RateLimiter limiter = RateLimiter.create(1);
        Thread.sleep(1000); // sleep1秒，创建一个令牌
        for(int i = 1; i < 5; i ++  ) {  //足够预消息的，就无需等待
            double waitTime = limiter.acquire(10);
            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
        }
    }

    @Test
    public void test2(){
        RateLimiter limiter = RateLimiter.create(1);
        for(int i = 1; i < 10; i = i + 2 ) {
            boolean flag = limiter.tryAcquire(0, TimeUnit.SECONDS);
            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + flag );
        }
    }



}
