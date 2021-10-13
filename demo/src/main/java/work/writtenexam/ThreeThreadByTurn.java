package work.writtenexam;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

/**
 * 三个线程依次执行，你能写几种方案呢
 */
public class ThreeThreadByTurn {

    /**
     * 利用join，main先等待t1，在等待t2
     */
    @Test
    public void test() throws InterruptedException {
        Thread t1 = new Thread(()->{
            Thread.yield();
            System.out.println("t1");
        });

        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });

        Thread t3 = new Thread(()->{
            System.out.println("t3");
        });
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        Thread.sleep(100);
    }

    @Test
    public void test2() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1");
        });

        Thread t2 = new Thread(()->{
            try {
                semaphore.release();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(()->{
            semaphore.release();
            System.out.println("t3");
        });

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(1000);
    }


    /**
     * 1.8 新特性
     */
    @Test
    public void test3()throws Exception{
        Thread t1 = new Thread(new MyThread());
        Thread t2 = new Thread(new MyThread());
        Thread t3 = new Thread(new MyThread());
        CompletableFuture<Void> ss = CompletableFuture.runAsync(() -> t1.start())
                .thenRun(() -> t2.start())
                .thenRun(() -> t3.start());
        System.out.println(ss.get());//可能比t3执行的早
    }

    static class MyThread implements Runnable{
        @Override
        public void run() {
            System.out.println("thread start : " + Thread.currentThread().getName());
        }
    }

}
