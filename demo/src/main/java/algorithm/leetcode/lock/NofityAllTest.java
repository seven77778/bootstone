package algorithm.leetcode.lock;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lsh
 * @date 2023/7/3 18:04
 * <p>
 * notify：唤醒一个线程，其他线程依然处于wait的等待唤醒状态，
 * 如果被唤醒的线程结束时没调用notify，其他线程就永远没人去唤醒，只能等待超时，或者被中断
 * <p>
 * notifyAll：所有线程退出wait的状态，开始竞争锁，
 * 但只有一个线程能抢到，这个线程执行完后，其他线程又会有一个幸运儿脱颖而出得到锁
 */
public class NofityAllTest {
  private AtomicInteger count = new AtomicInteger();


  @Test
  public void ww(){
    System.out.println(new Random().nextInt());
    System.out.println(new Random().nextInt(5));
  }

  @Test
  public void test() throws Exception {
    NofityAllTest main = new NofityAllTest();
    for (int i = 0; i < 5; i++) {
      new Thread(main::testWait).start();
    }
    Thread.sleep(1000);
    // 一个notifyAll为什么全都拿到锁了
    main.testNotifyAll();
    //全部退出wait之后，就一直保持抢夺锁的状态？
    //都能执行完是因为所以线程都在抢夺锁，第一个抢到，执行完后，释放了锁，其他线程又抢到。
    new Thread().join();

    Thread.sleep(9999);
  }

  @Test
  public void test2() throws Exception{
    NofityAllTest main = new NofityAllTest();
    // 开启两个线程去执行test方法
    for (int i = 0; i < 10; i++) {
      new Thread(() -> main.testWait()).start();
    }
    Thread.sleep(1000);
    //执行 了5次notify ，只有5个线程走完了，其余的还在wait
    for (int i = 0; i < 5; i++) {
      main.testNotify();
    }

    Thread.sleep(9999);
  }

  private synchronized void testNotifyAll() {
    notifyAll();
  }

  private synchronized void testWait() {
    try {
      log("进入了同步方法，开始wait");
      wait();
      Thread.sleep(new Random().nextInt(2000));
      log("wait结束");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private synchronized void testNotify() {
    notify();
  }

  private void log(String s) {
    System.out.println(count.incrementAndGet() + " "

      + "\t" + Thread.currentThread().getName() + " " + s);
  }
}
