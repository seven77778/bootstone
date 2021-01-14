package com.lsh.demo.basic.juc.synchronize;


import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class synchronizedTest {
/*

  pulbic class Something(){
         public synchronized void isSyncA(){}
         public synchronized void isSyncB(){}
         public static synchronized void cSyncA(){}
         public static synchronized void cSyncB(){}
     }
   那么，加入有Something类的两个实例a与b，那么下列组方法何以被1个以上线程同时访问呢
   a.   x.isSyncA()与x.isSyncB()
   b.   x.isSyncA()与y.isSyncA()
   c.   x.cSyncA()与y.cSyncB()
   d.   x.isSyncA()与Something.cSyncA()
 */

    @Test
    public void test() {
        SomeThing someThingA = new SomeThing();
        SomeThing someThingB = new SomeThing();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                someThingA.onlySyncA();
                someThingB.onlySyncA();
            }).start();
        }
    }

    @Test
    public void test2() throws Exception {
        //static 加的是对象锁,staticAndSyncA  和 staticAndSyncB 只能同时有一把锁，线程会阻塞，不同同时访问
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    SomeThing.staticAndSyncA();
                } catch (Exception e) {
                    System.out.println(e);
                }
                SomeThing.staticAndSyncB();
                countDownLatch.countDown();
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }


    @Test
    public void test3() {
        int i = 0;
        for (; ; ) {
            i++;
            if (i == 5) {
                return;
            }
            System.out.println(i);
        }
    }


}
