package algorithm.leetcode.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author lsh
 * @date 2023/7/4 15:09
 * <p>
 * 线程 A：调用 zero() ，只输出 0
 * 线程 B：调用 even() ，只输出偶数
 * 线程 C：调用 odd() ，只输出奇数
 */
public class ZeroEvenOdd {

  private int n;

  public ZeroEvenOdd(int n) {
    this.n = n;
  }


  /**********************CyclicBarrier***************************************************/

  volatile int num = 1;
  CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
  //设置2个位一组很关键，01一组，02一组，找到这个规律也就做出来了
  volatile boolean flag = true;

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero2(IntConsumer printNumber) throws InterruptedException {
    while (num <= n) {
      if (flag) {
        printNumber.accept(0);

        flag = false;
        try {
          cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
      } else {
        Thread.yield();
      }
    }

  }

  public void even2(IntConsumer printNumber) throws InterruptedException {
    while (num <= n) {
      if (num % 2 == 0 && !flag) {
        printNumber.accept(num);
        num++;
        flag = true;
        try {
          cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
      } else {
        Thread.yield();
      }
    }
  }

  public void odd2(IntConsumer printNumber) throws InterruptedException {
    while (num <= n) {
      if (num % 2 != 0 && !flag) {
        printNumber.accept(num);
        num++;
        flag = true;
        try {
          cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
      } else {
        Thread.yield();
      }
    }
  }


  /**********************信号量***************************************************/

  Semaphore zero = new Semaphore(1);
  Semaphore even = new Semaphore(1);
  Semaphore odd = new Semaphore(1);


  volatile int zzz = 1;
  volatile boolean flagZero = true;

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero4(IntConsumer printNumber) throws InterruptedException {
    while (zzz <= n) {
      if (flagZero) {
        zero.acquire(1);
        printNumber.accept(0);
        if (zzz % 2 == 0) {
          even.release();
        } else {
          odd.release();
        }
        flagZero = false;
      }
    }
//    zero.release();
//    even.release();


  }

  public void even4(IntConsumer printNumber) throws InterruptedException {

    while (zzz <= n) {
      even.acquire();
      if (zzz % 2 == 0 && !flagZero) {
        printNumber.accept(zzz);
        zzz++;
        flagZero = true;
        zero.release();
      }
    }
//    even.release();
//    odd.release();

  }

  public void odd4(IntConsumer printNumber) throws InterruptedException {

    while (zzz <= n) {
      odd.acquire();
      if (zzz % 2 != 0 && !flagZero) {
        printNumber.accept(zzz);
        zzz++;
        flagZero = true;
        zero.release(1);
      }
    }
//    odd.release();
//    even.release();
  }

  /******************这个信号量，如何保证z总是先执行呢********************************/
  //e/o的信号量先初始化为0，等待z释放，妙啊！

  private Semaphore z = new Semaphore(1);
  private Semaphore e = new Semaphore(0);
  private Semaphore o = new Semaphore(0);
  public void zero(IntConsumer printNumber) throws InterruptedException {
    for(int i = 0; i < n; i++){
      z.acquire();
      printNumber.accept(0);
      if(i % 2 == 0){
        o.release();
      } else{
        e.release();
      }
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    for(int i = 2;i <= n;i += 2){
      e.acquire();
      printNumber.accept(i);
      z.release();
    }
  }

  public void odd(IntConsumer printNumber) throws InterruptedException {

    for(int i = 1;i <= n;i += 2){
      o.acquire();
      printNumber.accept(i);
      z.release();
    }
  }

  /****************************TEST*******************************/

  public static void main(String[] args) {
    ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(7);
    new Thread(() -> {
      try {
        zeroEvenOdd.zero(x -> {
          System.out.print(x);
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "zero").start();
    new Thread(() -> {
      try {
        zeroEvenOdd.even(x -> {
          System.out.print(x);
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "even").start();
    new Thread(() -> {
      try {
        zeroEvenOdd.odd(x -> {
          System.out.print(x);
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "odd").start();


  }


}
