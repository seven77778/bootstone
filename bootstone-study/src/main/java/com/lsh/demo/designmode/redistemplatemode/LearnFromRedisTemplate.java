package com.lsh.demo.designmode.redistemplatemode;

import org.junit.Test;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by LSH on 2018/11/14.
 *
 * @author LSH
 * @date 2018/11/14
 *
 * 通过redistemplate 学习设计模式
 * 模板方法模式
 *
 */
public class LearnFromRedisTemplate {
    @Test
    public void test(){
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        redisTemplate.execute((RedisCallback) connection -> {
            //return connection
            return null;
        });
    }
}
