package com.lsh.demo.basic.system.jvm.basicjvm.volatiletest;

import org.junit.Test;

import java.io.Serializable;

/**
 * volatile 1.可见性
 * 2.禁止重排序
 * <p>
 * volatile final 不能同时出现
 */
public class VolatileTest implements Serializable {
    public volatile int num = 0;


    public void add() {
        for (int i = 0; i < 1000; i++) {
            num++;
        }
    }


    /**
     * 可以知道volatile并不适用这里
     * <p>
     * 理想结果是20000，测试大多是num : 19000
     */
    @Test
    public void test() throws Exception {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(() -> add());
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            System.out.println(Thread.activeCount());
            Thread.yield();
        }
        System.out.println("num : " + num);
        //展示当前活动线程
        Thread.currentThread().getThreadGroup().list();
    }


    public static void main(String[] args) throws Exception {
        {
            {
                System.out.println("222");
                {
                    System.out.println("123");
                }
            }
        }
    }

}
