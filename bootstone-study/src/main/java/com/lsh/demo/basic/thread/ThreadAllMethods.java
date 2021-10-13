package com.lsh.demo.basic.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by lsh on 2019-09-06.
 * <p>
 * wait: 释放当前锁，阻塞直到被notify或notifyAll唤醒，或者超时，或者线程被中断(InterruptedException)
 * notify: 任意选择一个（无法控制选哪个）正在这个对象上等待的线程把它唤醒，其它线程依然在等待被唤醒
 * notifyAll: 唤醒所有线程，让它们去竞争，不过也只有一个能抢到锁
 * sleep: 不是Object中的方法，而是Thread类的静态方法，让当前线程持有锁阻塞指定时间
 */
public class ThreadAllMethods {

    //只是执行了一个中断方法，while会停止吗
    @Test
    public void test2() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println("goon");
            }
        });
        t1.start();
        System.out.println("end");
        t1.interrupt();
    }

    @Test
    public void test() throws Exception{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("while");
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("interrupt");
                        break;
                    }
                }
            }
        });
        t1.wait();
        t1.notify();
        t1.start();
        System.out.println("end");
//        Thread.sleep(10);
        t1.interrupt();
    }


    //线程中断

    @Test
    public void testin(){
        Thread t1 = new Thread(()->{

            while (true)
                System.out.println(Thread.currentThread().isInterrupted());
        });

        t1.start();
//        LockSupport.park();
        t1.interrupt();
        System.out.println(t1.currentThread().isInterrupted());

    }



}
