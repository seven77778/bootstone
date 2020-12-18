package com.lsh.demo.basic.thread;

import org.junit.Test;

/**
 * test yeild
 * join
 */
public class ThreadYeild {



    /**
     * yield -- 暂停当前正在执行的线程对象，并执行其他线程
     * yield的目的是让相同优先级的线程轮转执行，但是不一定能成功
     * 比如大家都在等公交车，现在的第一名退后了两步，但是还有可能第一个上车
     *
     *
     */
    @Test
    public void test() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("这是线程-1");
                if(i==10){
                    Thread.yield();
                    System.out.println("这是线程-1-yield");
                }
            }
        }
        );
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("这是线程-2");
            }
        }
        );
        t1.start();
        t2.start();
    }

    /**
     * join 让“主线程”等待“子线程”结束之后才能继续运行
     * 不加join的话，主线程end最先执行，加了join，变成最后执行
     * join方法被 synchronized 修饰
     */
    @Test
    public void testJoin()throws Exception{
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("这是线程-1");
            }
        }
        );
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("这是线程-2");
            }
        }
        );
        t1.start();
        t2.start();
        t2.join();
        t1.join();
        System.out.println("主线程end");
    }

}
