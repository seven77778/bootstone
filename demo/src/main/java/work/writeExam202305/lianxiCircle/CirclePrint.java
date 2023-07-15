package work.writeExam202305.lianxiCircle;

import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lsh
 * @date 2023/5/15 15:48
 */
public class CirclePrint {

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

    /**
     * 这种写法，会导致、
     * Thread1：100
     * Thread2：101--也对，之前写错位置了
     */

    Thread t1, t2, t3;

    @Test
    public void test() throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        t1 = new Thread(() -> {
            while (true) {
                System.out.println(2222222);
                longAdder.increment();
                if (longAdder.intValue() > 100) {
                    return;
                } else {
                    System.out.println(Thread.currentThread().getName() + "：" + longAdder.intValue());
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }

            }


        }, "Thread1");

        t2 = new Thread(() -> {
            while (true) {
                System.out.println(2222222);
                LockSupport.park();
                longAdder.increment();
                if (longAdder.intValue() > 100) {
                    return;//return 退出整个方法了
                } else {
                    System.out.println(Thread.currentThread().getName() + "：" + longAdder.intValue());
                    LockSupport.unpark(t3);
                }

            }

        }, "Thread2");

        t3 = new Thread(() -> {
            while (true) {
                System.out.println(2222222);
                LockSupport.park();
                longAdder.increment();
                if (longAdder.intValue() > 100) return;

                System.out.println(Thread.currentThread().getName() + "：" + longAdder.intValue());
                LockSupport.unpark(t1);
            }

        }, "Thread3");
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(1000);

    }
}
