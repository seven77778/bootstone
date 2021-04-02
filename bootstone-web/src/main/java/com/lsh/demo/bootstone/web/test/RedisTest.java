package com.lsh.demo.bootstone.web.test;


import com.lsh.demo.bootstone.service.util.RedisUtil;
import com.lsh.demo.bootstone.web.BootStoneWebApplication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.utils.RedisFinalUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BootStoneWebApplication.class})
public class RedisTest {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisFinalUtil redisFinalUtil;

    @Test
    public void testBasic() {
        String str = redisFinalUtil.getStringKey("key");
        System.out.println(str);
    }

    /**
     * 2.3.5切换看似成功，取值还是0库，2.0.6 可以切换成功，2.1.3成功
     * 看似切换成功，只是redisTemplate中看db是9，但是取值还是0
     */
    @Test
    public void testDB() {
        String str = redisFinalUtil.getDB("key",9);
        String str2 = redisFinalUtil.getDB("key",9);
        String str4 = redisFinalUtil.getDB("key",9);
        System.out.println(str);
    }

    @Test
    public void testRedisInvokeRedis() {
        String script = "local times = redis.call('incr',KEYS[1])\n" +
                "\n" +
                "if times == 1 then\n" +
                "    redis.call('expire',KEYS[1], ARGV[1])\n" +
                "end\n" +
                "\n" +
                "if times > tonumber(ARGV[2]) then\n" +
                "    return 0\n" +
                "end\n" +
                "return 1";
        List<String> key = Lists.newArrayList();
        key.add("lshlsh123");
        //测试使用的lua脚本
//        script="return 545456";
        String res = redisUtil.invokeScript(script, key, "10", "20");
        System.out.println(res);
    }

    @Test
    public void testRedisGet() {
        String res = redisUtil.get("key");
        System.out.println(res);
    }

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test() throws Exception {
        //通过redissonClient创造一个key为xxx的信号量
        RPermitExpirableSemaphore semaphore = redissonClient.getPermitExpirableSemaphore("xxx");
        //给信号量设置数量为5，用在限流中就是只允许5次请求
        while (!semaphore.trySetPermits(5)) ;

        //tryAcquire 的第一个参数是waittime，尝试获得许可证的最大等待时间，超过这个时间则返回null
        //第二个参数是许可证的过期时间，也是精华所在，即使发生宕机，jvm崩溃等情况，也不用担心，信号量过期会自动释放
        //成功之后会获得一个许可证ID，是在获取期间每次生成的128位唯一随机标识符
        String permitId = semaphore.tryAcquire(1, 5, TimeUnit.SECONDS);
        System.out.println(permitId);
        //释放许可证还需要之前获得permitId
        semaphore.release(permitId);
    }
}
