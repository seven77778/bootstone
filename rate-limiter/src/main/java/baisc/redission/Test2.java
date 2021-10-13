package baisc.redission;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * 编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，3个线程交替打印1-100的整数，要求输出结果有序,
 *   样例Sample:
 * Thread1: 1
 * Thread2: 2
 * Thread3: 3
 * Thread1: 4
 * Thread2: 5
 * Thread3: 6
 * ....
 * Thread3: 99
 * Thread1: 100
 *
 * LockSupport、synchronize也都可以
 **/
public class Test2 {

    static LongAdder adder = new LongAdder();
    static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws Throwable {
        Semaphore semaphore1 = new Semaphore(1);//thread1先开始
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        new Thread(() -> {
            while (countDownLatch.getCount()>0){
                try {
                    semaphore1.acquire();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adder.increment();
                System.out.println(Thread.currentThread().getName() + ":" + adder.intValue());
                if(countDownLatch.getCount()!=0){
                    //在执行到100的时候结束，这里写的不太优雅
                    semaphore2.release();
                }else {
                    //直接中断，只是为了100暂停下来
                    semaphore2.acquireUninterruptibly();
                }
            }
        }, "Thread1").start();

        new Thread(() -> {
            while (countDownLatch.getCount()>0){
                try {
                    semaphore2.acquire();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adder.increment();
                System.out.println(Thread.currentThread().getName() + ":" + adder.intValue());
                semaphore3.release();
            }
        }, "Thread2").start();

        new Thread(() -> {
            while (countDownLatch.getCount()>0) {
                try {
                    semaphore3.acquire();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adder.increment();
                System.out.println(Thread.currentThread().getName() + ":" + adder.intValue());
                semaphore1.release();

            }
        }, "Thread3").start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
