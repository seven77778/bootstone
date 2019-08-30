package com.lsh.demo.basic.juc.thread;

import org.junit.Test;

/**
 * Created by lsh on 2019-08-27.
 *
 * 为什么不用sleep 来当做定时器
 * 1.不能做到严格的时间控制 ：
 * 因为使用sleep方法之后，线程是进入阻塞状态的，只有当睡眠的时间结束，才会重新进入到就绪状态，而就绪状态进入到运行状态，
 * 是由系统控制的，我们不可能精准的去干涉它，所以如果调用Thread.sleep(1000)使得线程睡眠1秒，可能结果会大于1秒
 *
 * 程序不可能做到精准控制
 *
 * 而且占着一个线程不干活，相当于浪费资源
 */
public class ThreadTest {

    int x = 0;


    @Test
    public void test() {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> System.out.println("当前i = " + ++x)).start();
        }

        System.out.println("last is " + x);
    }

    // 请问输出结果是什么？todo
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable running..");
            }

        }) {
            @Override
            public void run() {
                System.out.println("Thread running..");
            }
            ;
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(11);
            }

            {
                System.out.println(22);
            }
        }).start();
    }
}
