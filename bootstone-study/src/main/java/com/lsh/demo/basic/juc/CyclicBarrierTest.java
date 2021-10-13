package com.lsh.demo.basic.juc;

import com.lsh.demo.basic.thread.threadpool.MyBasicThreadFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by lsh on 2019/2/25.
 *
 * 一种同步辅助工具，允许一组线程相互等待*对方到达共同的屏障点。
 * 在包含固定大小的线程组的程序中(这些线程组偶尔必须相互等待)，cyclicBarrier 是非常有用的。
 * 这个屏障被称为* <em> cycle </em>，因为它可以在等待的线程*释放后被重用。
 */
public class CyclicBarrierTest {

    // 请求的数量
   static LongAdder longAdder = new LongAdder();
    // 需要同步的线程数量
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ExecutorService threadPool = MyBasicThreadFactory.getExecutorService(10);

        for (int i = 0; i < 50; i++) {
            Thread.sleep(10);
            threadPool.execute(() -> {
                try {
                    test(longAdder.intValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
        cyclicBarrier.reset();
    }

    public static void test(int threadnum) throws InterruptedException, BrokenBarrierException {
        longAdder.increment();
        System.out.println("threadnum:" + threadnum + "is ready");
        try {
            /**等待60秒，保证子线程完全执行结束*/
            cyclicBarrier.await(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("-----CyclicBarrierException------");
        }
        System.out.println("threadnum:" + threadnum + "is finish");
    }


}
