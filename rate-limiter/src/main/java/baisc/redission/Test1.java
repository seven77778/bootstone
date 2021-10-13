package baisc.redission;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

public class Test1 {


    static Thread thread1, thread2, thread3;
    static LongAdder adder = new LongAdder();
    static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws Exception {
        thread1 = new Thread(() -> {
            while (countDownLatch.getCount()>0) {
                adder.increment();
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + ":" + adder.intValue());
                if(countDownLatch.getCount()!=0){
                    LockSupport.unpark(thread2);
                    LockSupport.park();
                }
            }
        }, "Thread1");
        thread2 = new Thread(() -> {
            while (countDownLatch.getCount()>0) {
                LockSupport.park();
                adder.increment();
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + ":" + adder.intValue());
                LockSupport.unpark(thread3);
            }
        }, "Thread2");
        thread3 = new Thread(() -> {
            while (countDownLatch.getCount()>0) {
                LockSupport.park();
                adder.increment();
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + ":" + adder.intValue());
                LockSupport.unpark(thread1);
            }
        }, "Thread3");
        thread1.start();
        thread2.start();
        thread3.start();
        countDownLatch.await();

    }

}
