package com.lsh.demo.lock.test;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/6 17:14.
 *
 * @author lsh
 * @date 2018/11/06
 *
 * 对象锁和类锁
 */
public class SyncTest3 {

    synchronized static void hello(){
        while (true) {
            System.out.println("hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void hi(){
        System.out.println("hi");
    }

    @Test
    public void test(){
        SyncTest3 syncTest3 = new SyncTest3();
        new Thread(()->syncTest3.hello()).start();

        SyncTest3 syncTest31 = new SyncTest3();
        new Thread(()->syncTest31.hi()).start();

        while (Thread.activeCount()>2){
            Thread.yield();
        }
    }
}
