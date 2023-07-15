package algorithm.leetcode.thread;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author lsh
 * @date 2023/7/3 21:12
 * <p>
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 * <p>
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 */
public class FizzBuzz {

  private int n;



  public FizzBuzz() {
  }


  AtomicInteger all = new AtomicInteger(1);

  // printFizz.run() outputs "fizz".
  public void fizz2(Runnable printFizz) throws InterruptedException {
    synchronized (this) {
      while (all.intValue() <= n) {
        if (all.intValue() % 3 == 0 && all.intValue() % 5 != 0) {
          printFizz.run();
          all.incrementAndGet();
          this.notifyAll();
        } else {
          this.wait();
        }
      }
    }


  }

  // printBuzz.run() outputs "buzz".
  public void buzz2(Runnable printBuzz) throws InterruptedException {
    synchronized (this) {
      while (all.intValue() <= n) {
        if (all.intValue() % 5 == 0 && all.intValue() % 3 != 0) {
          printBuzz.run();
          all.incrementAndGet();
          this.notifyAll();
        } else {
          this.wait();
        }
      }
    }
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz2(Runnable printFizzBuzz) throws InterruptedException {
    synchronized (this) {
      while (all.intValue() <= n) {
        if (all.intValue() % 5 == 0 && all.intValue() % 3 == 0) {
          all.incrementAndGet();
          printFizzBuzz.run();
          this.notifyAll();//不能放在最后，因为本身wait了，notifyAll也可能通知自己，就一直阻塞
        } else {
          this.wait();
        }
//        this.notifyAll();
      }
    }

  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number2(IntConsumer printNumber) throws InterruptedException {
    synchronized (this) {
      while (all.intValue() <= n) {
        if (all.intValue() % 3 != 0 && all.intValue() % 5 != 0) {
          printNumber.accept(all.intValue());
          all.incrementAndGet();
          this.notifyAll();
        } else {
          this.wait();
        }
      }
    }

  }


  /***********************reentrantlock 也是OK的***************************/
  volatile int num = 1;
  ReentrantLock lock = new ReentrantLock();
  Condition condition = lock.newCondition();

  // printFizz.run() outputs "fizz".
  public void fizz3(Runnable printFizz) throws InterruptedException {
    lock.lock();
    while (num <= n) {
      if (num % 3 == 0 && num % 5 != 0) {
        printFizz.run();
        num++;
        condition.signalAll();
      } else {
        condition.await();
      }
    }
    lock.unlock();
  }

  // printBuzz.run() outputs "buzz".
  public void buzz3(Runnable printBuzz) throws InterruptedException {

    lock.lock();
    while (num <= n) {
      if (num % 5 == 0 && num % 3 != 0) {
        printBuzz.run();
        num++;
        condition.signalAll();
      } else {
        condition.await();
      }
    }
    lock.unlock();
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz3(Runnable printFizzBuzz) throws InterruptedException {
    lock.lock();
    while (num <= n) {
      if (num % 3 == 0 && num % 5 == 0) {
        printFizzBuzz.run();
        num++;
        condition.signalAll();
      } else {
        condition.await();
      }
    }
    lock.unlock();
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number3(IntConsumer printNumber) throws InterruptedException {

    lock.lock();
    while (num <= n) {
      if (num % 3 != 0 && num % 5 != 0) {
        printNumber.accept(num);
        num++;
        condition.signalAll();
      } else {
        condition.await();
      }
    }
    lock.unlock();
  }


  /***********************试试用信号量 fixme ***************************/
  volatile int sum = 1;
  Semaphore semaphore = new Semaphore(1);



  // printFizz.run() outputs "fizz".
  public void fizz(Runnable printFizz) throws InterruptedException {
    semaphore.acquire();

    while (num <= n) {
      if (num % 3 == 0 && num % 5 != 0) {
        printFizz.run();
        num++;
        condition.signalAll();
      } else {
        condition.await();
      }
    }
    lock.unlock();
  }

  // printBuzz.run() outputs "buzz".
  public void buzz(Runnable printBuzz) throws InterruptedException {

    lock.lock();
    while (num <= n) {
      if (num % 5 == 0 && num % 3 != 0) {
        printBuzz.run();
        num++;
        condition.signalAll();
      } else {
        condition.await();
      }
    }
    lock.unlock();
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
    lock.lock();
    while (num <= n) {
      if (num % 3 == 0 && num % 5 == 0) {
        printFizzBuzz.run();
        num++;
        condition.signalAll();
      } else {
        condition.await();
      }
    }
    lock.unlock();
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number(IntConsumer printNumber) throws InterruptedException {

    lock.lock();
    while (num <= n) {
      if (num % 3 != 0 && num % 5 != 0) {
        printNumber.accept(num);
        num++;
        condition.signalAll();
      } else {
        condition.await();
      }
    }
    lock.unlock();
  }









  /************************以下是Test******************************/


  @Test
  public void test() throws Exception {
    FizzBuzz fizzBuzz = new FizzBuzz();
    fizzBuzz.n = 15;
    // 下面三个Thread顺序可以任意掉换

    new Thread(() -> {
      try {
        fizzBuzz.number((x) -> {
          System.out.println(x);
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        fizzBuzz.fizz(() -> System.out.println("fizz"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        fizzBuzz.buzz(() -> System.out.println("buzz"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

  }

}
