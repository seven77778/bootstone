package algorithm.leetcode.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lsh
 * @date 2023/7/3 19:55
 * <p>
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 */
public class Foo {

  public Foo() {

  }

  AtomicInteger atomicInteger = new AtomicInteger(0);


  /********************************CAS自旋*****************************/

  public void first2(Runnable printFirst) throws InterruptedException {
    // printFirst.run() outputs "first". Do not change or remove this line.
    printFirst.run();
    atomicInteger.incrementAndGet();
  }

  public void second2(Runnable printSecond) throws InterruptedException {
    // printSecond.run() outputs "second". Do not change or remove this line.
    while (atomicInteger.intValue() != 1) {
      Thread.yield();//加上yield之后，只要8ms
    }
    printSecond.run();
    atomicInteger.incrementAndGet();
  }

  public void third2(Runnable printThird) throws InterruptedException {
    // printThird.run() outputs "third". Do not change or remove this line.
    while (atomicInteger.intValue() != 2) {
      Thread.yield();//加上yield之后，只要8ms
    }
    printThird.run();
    atomicInteger.incrementAndGet();
  }


  /************************** 测试第二种 *********************************/
  volatile int i = 0; // 加了volatile就过了，first修改了i，其他线程不可见啊

  //得用while，来实现等待队列的作用
  public void first4(Runnable printFirst) throws InterruptedException {
    // printFirst.run() outputs "first". Do not change or remove this line.
    synchronized (this) {
      printFirst.run();
      i = 1;
      this.notifyAll();
    }

  }

  public void second4(Runnable printSecond) throws InterruptedException {
    // printSecond.run() outputs "second". Do not change or remove this line.
    synchronized (this) {
      while (i != 1) {
        this.wait();
      }
      printSecond.run();
      i = 2;
      this.notifyAll();
    }


  }

  public void third4(Runnable printThird) throws InterruptedException {
    // printThird.run() outputs "third". Do not change or remove this line.
    synchronized (this) {
      while (i != 2) {
        this.wait();
      }
      printThird.run();
      i++;
      this.notifyAll();
    }


  }
  /************************** 信号量 *********************************/

  Semaphore second =new Semaphore(0);
  Semaphore three =new Semaphore(0);

  public void first(Runnable printFirst) throws InterruptedException {
    // printFirst.run() outputs "first". Do not change or remove this line.
      printFirst.run();
      second.release(1);


  }

  public void second(Runnable printSecond) throws InterruptedException {
    // printSecond.run() outputs "second". Do not change or remove this line.

      second.acquire(1);
      printSecond.run();
      three.release(1);


  }

  public void third(Runnable printThird) throws InterruptedException {
    // printThird.run() outputs "third". Do not change or remove this line.
      three.acquire();
      printThird.run();

  }

  /****************************TEST**************************************/
  //正确的测试方法
  public static void main(String[] args) {
    Foo foo = new Foo();
    // 下面三个Thread顺序可以任意掉换
    new Thread(() -> {
      try {
        foo.third(() -> System.out.println("Three"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        foo.second(() -> System.out.println("Two"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        foo.first(() -> System.out.println("One"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();


  }

}
