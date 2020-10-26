package com.lsh.demo.bootstone.web.test;


import com.lsh.demo.bootstone.service.util.RedisUtil;
import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={BootStoneWebApplication.class})
public class RedisTest {

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void testRedisInvokeRedis(){
        String script="local times = redis.call('incr',KEYS[1])\n" +
                "\n" +
                "if times == 1 then\n" +
                "    redis.call('expire',KEYS[1], ARGV[1])\n" +
                "end\n" +
                "\n" +
                "if times > tonumber(ARGV[2]) then\n" +
                "    return 0\n" +
                "end\n" +
                "return 1";
        List<String> key= Lists.newArrayList();
        key.add("lshlsh123");
        //测试使用的lua脚本
//        script="return 545456";
        String res= redisUtil.invokeScript(script,key,"10","20");
        System.out.println(res);
    }

    @Test
    public void testRedisGet(){
        String res = redisUtil.get("hh");
        System.out.println(res);
    }
}
