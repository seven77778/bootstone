package work.writeExam202305.lianxiCircle;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lsh
 * @date 2023/5/15 20:59
 */
public class UseReentrantLock {
    private int state = 0;
    private int count = 100;
    private LongAdder longAdder = new LongAdder();
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(1%3);
        System.out.println(2%3);
        System.out.println(0%3);
        System.out.println(4%3);
    }

    /**
     * 无用的执行较多，A拿到锁以后，会继续多次拿到锁，但是按照设想，应该是B拿到锁
     */
    public void print(String name, int target) {
        for (int i = longAdder.intValue(); i < 3; ) {
            lock.lock();
            System.out.println("i==" + i +"---" +name);
            if (state % 3 == target) {
                System.out.println("进来了");
                state++;
                longAdder.increment();
                System.out.println(name + ":" + (i = longAdder.intValue()));
            } else {
                System.out.println("No");
            }
            lock.unlock();
        }
    }


    @Test
    public void test() throws InterruptedException, ExecutionException {
        UseReentrantLock useReentrantLock = new UseReentrantLock();
        Thread t1 = new Thread(() -> useReentrantLock.print("A", 0));
        Thread t2 = new Thread(() -> useReentrantLock.print("B", 1));
        Thread t3 = new Thread(() -> useReentrantLock.print("C", 2));

        CompletableFuture<Void> sss = CompletableFuture.runAsync(() -> t1.start()).thenRun(() -> t2.start()).thenRun(() -> t3.start());

        Thread.sleep(1000);
        System.out.println(sss.get());
    }


}
