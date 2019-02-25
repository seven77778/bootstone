package com.lsh.demo.lua.luatest;


import jodd.util.StringUtil;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by wb-lsh301927 on 2018/1/16.
 *
 * @author wb-lsh301927
 * @date 2018/01/16
 */
public class LuaDemo extends Thread implements Runnable {

    /**
     * 保存每个线程独有的token
     */
    private static ThreadLocal<String> tokenMap = new ThreadLocal<>();
    static Jedis jedis = new Jedis("localhost");

    /**
     * redis实现分布式可重入锁,并不保证在过期时间内完成锁定内的任务，需根据业务逻辑合理分配seconds
     *
     * @param lock
     *            锁的名称
     * @param mseconds
     *            锁定时间，单位 毫秒
     *  token 对于同一个lock,相同的token可以再次获取该锁，不相同的token线程需等待到unlock之后才能获取
     *
     */
    public  boolean lock(final String lock,  int mseconds) {
        Jedis jedis = new Jedis("localhost");
        // token 对于同一个lock,相同的token可以再次获取该锁，不相同的token线程需等待到unlock之后才能获取
        String token = tokenMap.get();
        if (StringUtil.isBlank(token)) {
            token = UUID.randomUUID().toString().replaceAll("-","");
            tokenMap.set(token);
        }
        boolean flag = false;
        try {
            String ret = jedis.set(lock, token, "NX", "PX", mseconds);
            if (ret == null) {// 该lock的锁已经存在
                String origToken = jedis.get(lock);// 即使lock已经过期也可以
                if (token.equals(origToken) || origToken==null) {// token相同默认为同一线程，所以token应该尽量长且随机，保证不同线程的该值不相同
                    ret = jedis.set(lock, token, "NX", "PX", mseconds);//
                    if ("OK".equalsIgnoreCase(ret))
                        flag = true;
                    System.out.println("当前线程 " + token);
                }
            } else if ("OK".equalsIgnoreCase(ret))
                flag = true;
            System.out.println("当前线程 " + token);
        } catch (Exception e) {

        } finally {
            if (jedis != null)
                jedis.close();
        }
        return flag;
    }

    /**
     * redis可以保证lua中的键的原子操作 unlock:lock调用完之后需unlock,否则需等待lock自动过期
     *
     * @param lock
     *  token
     *            只有线程已经获取了该锁才能释放它（token相同表示已获取）
     */
    public  void unlock( String lock) {
        Jedis jedis = new Jedis("localhost");
        //Object ss = jedis.evalsha("42deb81345ff2d34c1ca65c04a6bdf16f347f936",1,"luaaa");
        //System.out.println("--ss " + ss);
        final String token = tokenMap.get();
        if (StringUtil.isBlank(token))
            return;
        try {
            final String script = "if redis.call(\"get\",\"" + lock + "\") == \"" + token + "\"then  return redis.call(\"del\",\"" + lock + "\") else return 0 end ";
            jedis.eval(script);

        } catch (Exception e) {
            throw new RedisException("error");
        } finally {
            if (jedis != null)
                jedis.close();
        }

    }


    @Test
    public void test() throws Exception{
        LuaDemo de = new LuaDemo();
        //boolean res = de.lock("demolock", 10);
        de.unlock("");
        System.out.println("-->" );
        Jedis jedis = new Jedis("localhost");
        //jedis.scriptLoad()
        jedis.setnx("lockName","value");
        jedis.expire("lockName",10);
    }




//-------------------------------------------------------

    @Test
    public void lock () throws Exception{
        Jedis jedis = new Jedis("localhost") ;
        InputStream input = new FileInputStream("D:\\workSpace0112\\redis_test\\busuac\\busuac-web\\src\\test"
            + "\\java\\com\\alitrip\\traveluac\\bus\\redisLua\\lock.lua");
        byte[] by = new byte[input.available()];
        input.read(by);
        String script = new String(by);
        Object obj = jedis.eval(script, Arrays.asList("lock","1","100000"), Arrays.asList(""));
        System.out.println("执行结果: " + obj);
    }



    @Test
    public void luaUnLock() throws Exception{
        Jedis jedis = new Jedis("localhost") ;
        InputStream input = new FileInputStream("D:\\workSpace0112\\redis_test\\busuac\\busuac-web\\src\\test"
            + "\\java\\com\\alitrip\\traveluac\\bus\\redisLua\\unLock.lua");
        byte[] by = new byte[input.available()];
        input.read(by);
        String script = new String(by);
        Object obj = jedis.eval(script, Arrays.asList("key","123"), Arrays.asList(""));
        System.out.println("执行结果: " + obj);
    }


    public static void limitIp()throws Exception{
        jedis = new Jedis("localhost");
        InputStream input = new FileInputStream("D:\\workSpace0112\\redis_test\\busuac\\busuac-web\\src\\test"
            + "\\java\\com\\alitrip\\traveluac\\bus\\redisLua\\limit.lua");
        byte[] by = new byte[input.available()];
        input.read(by);
        String script = new String(by);
        Object obj = jedis.eval(script, Arrays.asList("127.0.0.1"), Arrays.asList("3","1000"));
        System.out.println("执行结果:" + obj);
    }

    @Test
    public void testLimitIp() throws Exception{
        for(int k=1 ; k<21 ; k++){
            long currentTime = System.currentTimeMillis();
            Thread.sleep(100);
            limitIp();
            //System.out.println("第" + k + "次损耗时间" +(System.currentTimeMillis() - currentTime));
        }
    }






    @Test
    public void scriptLoad()throws Exception{
        Jedis jedis = new Jedis("localhost");
        //从文件读取lua脚本
        InputStream input = new FileInputStream("D:\\workSpace0112\\redis_test\\busuac\\busuac-web\\src\\test"
            + "\\java\\com\\alitrip\\traveluac\\bus\\redisLua\\return.lua");
        byte[] by = new byte[input.available()];
        input.read(by);
        byte[] scriptBy = jedis.scriptLoad(by);
        String sha1 = new String(scriptBy);
        //直接解析
        String sha2 = jedis.scriptLoad("local key1 = KEYS[1]\n"
            + "local key2 = KEYS[2]\n"
            + "local argv1 = ARGV[1]\n"
            + "return \"key1:\"..key1 ..\" key2:\"..key2.. \" argv1:\"..argv1");
        System.out.println("sha1 : " + sha1);
        System.out.println("sha2 : " + sha2);
        Object obj = jedis.evalsha(sha1, Arrays.asList("value1","value2"), Arrays.asList("value3"));
        System.out.println("执行结果： "+ obj);
    }


    @Test
    public void redissonTest()throws Exception{
        RedissonClient redisson = Redisson.create();

        RLock lock = redisson.getLock("foobar");

        lock.lock();


    }

}
