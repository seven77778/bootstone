package com.lsh.demo.bootstone.study.juc.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by lsh on 2019/1/8.
 */
public class ThreadLocalTest {

    private static final ThreadLocal<Long> timeLocal = new ThreadLocal<>();

    @Test
    public void test(){
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("mytest");
        threadLocal.get();
        System.out.println(threadLocal.get());
    }

    /**
     * 使用countdown计数  todo
     * @throws Exception
     */
    @Test
    public void test2() throws Exception{
        CountDownLatch count = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            timeLocal.set(System.currentTimeMillis());
            new Thread(()-> System.out.println(System.currentTimeMillis()-timeLocal.get()));
            count.countDown();
        }

        count.await();
    }
}
