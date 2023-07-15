package work.writeExam202305.lianxiCircle;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lsh
 * @date 2023/5/15 20:35
 * <p>
 * 这写法很漂亮！
 */
public class NewCriclePrint {

    List<Thread> threadList = Lists.newArrayList();
    int size = 3;
    int max = 100;
    int threadIndex = 0;
    int num = 1;


    @Test
    public void test() throws InterruptedException {
        for (int i = 1; i <= size; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    //阻塞当前线程
                    LockSupport.park();
                    //当前的值需要小于最大值
                    if (num <= max) {
                        System.out.println(Thread.currentThread().getName() + ":" + num++);
                    } else {
                        break;
                    }
                    //唤起下一个线程
                    LockSupport.unpark(threadList.get(++threadIndex % threadList.size()));
                }
                //唤起所有线程--todo 这里没必要啊
//                for (Thread thread1 : threadList) {
//                    LockSupport.unpark(thread1);
//                }
            });
            switch (i) {
                case 1:
                    thread.setName("ThreadA");
                    break;
                case 2:
                    thread.setName("ThreadB");
                    break;
                case 3:
                    thread.setName("ThreadC");
                    break;
            }

            threadList.add(thread);
        }

        //启动所有线程
        for (Thread thread : threadList) {
            thread.start();
        }

        //唤起第一个线程
        LockSupport.unpark(threadList.get(0));

        Thread.sleep(1000);
    }


    //CompletableFuture 新特性，线程编排功能
    @Test
    public void test3() throws Exception {
        Thread t1 = new Thread(new NewCriclePrint.MyThread());
        Thread t2 = new Thread(new NewCriclePrint.MyThread());
        Thread t3 = new Thread(new NewCriclePrint.MyThread());
        CompletableFuture<Void> ss = CompletableFuture.runAsync(() -> t1.start())
                .thenRun(() -> t2.start())
                .thenRun(() -> t3.start());
        Thread.sleep(1000);
        System.out.println(ss.get());//可能比t3执行的早
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("thread start : " + Thread.currentThread().getName());
        }
    }
}
