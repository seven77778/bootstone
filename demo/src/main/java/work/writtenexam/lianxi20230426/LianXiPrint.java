package work.writtenexam.lianxi20230426;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lsh
 * @date 2023/4/26 16:53
 */
public class LianXiPrint {

    static Thread t1, t2, t3;


    //循环打印线程1--A，2-B，3-c，4-a，……100-a
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        LongAdder longAdder = new LongAdder();

        t1 = new Thread(() -> {
            while (true) {
                longAdder.increment();
                if (longAdder.intValue() >= 10) {
                    return;
                } else {

                    System.out.println(Thread.currentThread().getName() + ":" + longAdder.intValue());
                    LockSupport.unpark(t2);

                    LockSupport.park();
                }

            }


        }, "ThreadA");


        t2 = new Thread(() -> {
            while (true) {
                longAdder.increment();
                if (longAdder.intValue() >= 10) {
                    return;
                } else {
                    LockSupport.park();

                    System.out.println(Thread.currentThread().getName() + ":" + longAdder.intValue());
                    LockSupport.unpark(t3);
                }

            }


        }, "ThreadB");

        t3 = new Thread(() -> {
            while (true) {
                longAdder.increment();
                if (longAdder.intValue() >= 10) {
                    return;
                } else {
                    LockSupport.park();
                    System.out.println(Thread.currentThread().getName() + ":" + longAdder.intValue());
                    LockSupport.unpark(t1);
                }

            }


        }, "ThreadC");

        t1.start();
        t2.start();
        t3.start();

//        countDownLatch.await(2, TimeUnit.SECONDS);
        System.out.println("end");
    }


    //之前   CyclicBarrier 的用法理解错误，以下是正确用法
    @Test
    public void testCyc() throws InterruptedException {
        CyclicBarrier cyc = new CyclicBarrier(2, () -> {
            System.out.println("2个人一组走喽");
        });


        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println("序号：" + finalI + "到了");
                    cyc.await();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
//            Thread.sleep(1000);

        }

        Thread.sleep(2000);

    }
}
