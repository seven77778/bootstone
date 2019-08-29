package com.lsh.demo.basic.juc.thread;

import org.junit.Test;

/**
 * Created by lsh on 2019-08-27.
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

            public void run() {
                System.out.println("Runnable running..");
            }

        }) {

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
