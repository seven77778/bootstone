package algorithm.leetcode.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author lsh
 * @date 2023/7/4 16:41
 */
public class H2O {

  public H2O() {

  }

  Semaphore h1 = new Semaphore(0);
  Semaphore o1 = new Semaphore(2);


  public void hydrogen2(Runnable releaseHydrogen) throws InterruptedException {

    // releaseHydrogen.run() outputs "H". Do not change or remove this line.
    h1.acquire();
    releaseHydrogen.run();
    o1.release();//每次H都会释放O,但是需要积攒2个才能获得一个O

  }

  public void oxygen2(Runnable releaseOxygen) throws InterruptedException {

    // releaseOxygen.run() outputs "O". Do not change or remove this line.

    o1.acquire(2);
    releaseOxygen.run();
    h1.release(2);
  }
  /**************************并发更高的方法********************************/

  Semaphore o = new Semaphore(2);//初始必须设置为2，最开始也可以通过O，然后必须通过两个H
  Semaphore h = new Semaphore(2);
  CyclicBarrier cyclicBarrier = new CyclicBarrier(3);


  public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

    // releaseHydrogen.run() outputs "H". Do not change or remove this line.
    h.acquire();
    try {
      cyclicBarrier.await();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
    releaseHydrogen.run();

    o.release();

  }

  public void oxygen(Runnable releaseOxygen) throws InterruptedException {

    // releaseOxygen.run() outputs "O". Do not change or remove this line.

    o.acquire(2);

    try {
      cyclicBarrier.await();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
    releaseOxygen.run(); // 为什么放到await的后面呢？ fixme -- 放哪都可以
    h.release(2);
  }



  /**************************TEST********************************/

  public static void main(String[] args) {
    H2O h2O = new H2O();

    new Thread(() -> {
      try {
        h2O.hydrogen(() -> {
          System.out.print("H");
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "hydrogen").start();

    new Thread(() -> {
      try {
        h2O.oxygen(() -> {
          System.out.print("O");
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "oxygen").start();

  }

}
