package com.lsh.demo.lua;

import org.junit.Test;
import org.redisson.client.RedisException;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by lsh on 2019/2/25.
 * <p>
 * 运行方式1
 * 在redis目录，通过cmd执行
 * redis-cli --eval 123.lua key1 key2 , 123
 * 123.lua 为脚本名称，空格 跟keys ，逗号之后 空格 跟 argv
 * <p>
 * 运行方式2
 * 运行redis-cli.exe
 * eval "return redis.call('get','hh')" 0
 * 注意点：0是keys个数
 */
public class LuaBasic {

    /**
     * 用redis来调用lua--直接加载
     * 外部加载script也OK
     */
    @Test
    public void test1() {
        try (Jedis jedis = new Jedis("localhost")) {
            final String script = "local key1 = KEYS[1]\n" +
                    "local key2 = KEYS[2]\n" +
                    "local argv1 = ARGV[1]\n" +
                    "return \"key1:\"..key1 ..\" key2:\"..key2.. \" argv1:\"..argv1";
            Object result = jedis.eval(script, Arrays.asList("key1", "ha2"), Collections.singletonList("arg"));
            System.out.println(result);
        } catch (Exception e) {
            throw new RedisException("error");
        }
    }
}
