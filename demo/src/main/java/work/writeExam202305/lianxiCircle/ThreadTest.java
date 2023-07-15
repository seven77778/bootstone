package work.writeExam202305.lianxiCircle;

/**
 * @author lsh
 * @date 2023/5/15 20:39
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class ThreadTest {
    static List<Thread> threadList = new ArrayList<>();//存放线程的集合
    static int threadSize = 3;//总共多少个线程
    static int threadIndex = 0;//当前线程下标
    static int maxValue = 100;//需要输出的数的最大值
    static int curValue = 1;//数的当前值

    public static void main(String[] args) throws InterruptedException {
        //创建线程
        for (int i = 1; i <= threadSize; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    //阻塞当前线程
                    LockSupport.park();
                    //当前的值需要小于最大值
                    if (curValue <= maxValue) {
                        System.out.println(Thread.currentThread().getName() + ":" + curValue++);
                    } else {
                        System.out.println("NOOO");
                        break;
                    }
                    //唤起下一个线程
                    LockSupport.unpark(threadList.get(++threadIndex % threadList.size()));
                }
                //唤起所有线程
                threadList.forEach(LockSupport::unpark);
            });
            thread.setName(String.format("Thread%d", i));
            threadList.add(thread);
        }

        //启动所有线程
        for (Thread thread : threadList) {
            thread.start();
        }

        //唤起第一个线程
        LockSupport.unpark(threadList.get(0));
    }
}
