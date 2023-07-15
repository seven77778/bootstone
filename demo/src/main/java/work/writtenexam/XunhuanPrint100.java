package work.writtenexam;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

public class XunhuanPrint100 {
    /**
     * 编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，3个线程交替打印1-100的整数，要求输出结果有序,
     * 样例Sample:
     * Thread1: 1
     * Thread2: 2
     * Thread3: 3
     * Thread1: 4
     * Thread2: 5
     * Thread3: 6
     * ....
     * Thread3: 99
     * Thread1: 100
     */

    //怎么也得写个三四个写法

    Thread t1, t2, t3;

    /**
     * CyclicBarrier throws timeoutException
     *
     * @throws Exception
     */
    @Test
    public void test1() {
        LongAdder longAdder = new LongAdder();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100);

        t1 = new Thread(() -> {
            for (int i = 0; i < 66; i++) {
                longAdder.increment();
                System.out.println(String.format("%s:%d", Thread.currentThread().getName(), longAdder.intValue()));
                ;
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "Thread1");

        t2 = new Thread(() -> {
            for (int i = 0; i < 66; i++) {
                LockSupport.park();
                longAdder.increment();
                System.out.println(String.format("%s:%d", Thread.currentThread().getName(), longAdder.intValue()));
                ;
                LockSupport.unpark(t3);

            }
        }, "Thread2");

        t3 = new Thread(() -> {
            for (int i = 0; i < 66; i++) {
                LockSupport.park();
                longAdder.increment();
                System.out.println(String.format("%s:%d", Thread.currentThread().getName(), longAdder.intValue()));
                ;
                LockSupport.unpark(t1);

            }
        }, "Thread3");

        t1.start();
        t2.start();
        t3.start();
        try {
            cyclicBarrier.await(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("end");
        }
        cyclicBarrier.reset();


        try {
            cyclicBarrier.await(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("222222222222end");
        }

    }


    /**
     * CountDownLatch不会报错，时间到了自动结束进程，cyc会抛异常
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        LongAdder longAdder = new LongAdder();
        CountDownLatch countDownLatch = new CountDownLatch(100);

        t1 = new Thread(() -> {
            for (int i = 0; i < 34; i++) {
                countDownLatch.countDown();
                longAdder.increment();
                System.out.println(String.format("%s:%d", Thread.currentThread().getName(), longAdder.intValue()));
                ;
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "Thread1");

        t2 = new Thread(() -> {
            for (int i = 0; i < 33; i++) {
                countDownLatch.countDown();
                LockSupport.park();
                longAdder.increment();
                System.out.println(String.format("%s:%d", Thread.currentThread().getName(), longAdder.intValue()));
                ;
                LockSupport.unpark(t3);

            }
        }, "Thread2");

        t3 = new Thread(() -> {
            for (int i = 0; i < 33; i++) {
                countDownLatch.countDown();
                LockSupport.park();
                longAdder.increment();
                System.out.println(String.format("%s:%d", Thread.currentThread().getName(), longAdder.intValue()));
                ;
                LockSupport.unpark(t1);

            }
        }, "Thread3");

        t1.start();
        t2.start();
        t3.start();
        countDownLatch.await(2, TimeUnit.SECONDS);

    }


    /**
     * 搞个优雅的写法, not do
     */

    LongAdder longAdder = new LongAdder();

    @Test
    public void testBeautiful() throws InterruptedException {
        Thread t1 = new Thread(new BeautifulThread(), "Thead1");
        Thread t2 = new Thread(new BeautifulThread(), "Thead2");
        Thread t3 = new Thread(new BeautifulThread(), "Thead3");
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);
    }

    class BeautifulThread implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    if (longAdder.intValue() == 100) {
                        Thread.interrupted();
                    }
                    longAdder.increment();
                    System.out.println(String.format("%s:%d", Thread.currentThread().getName(), longAdder.intValue()));
                }
            }

        }
    }
}
