package work.writeExam202305.lianxiCircle;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lsh
 * @date 2023/5/15 15:48
 * <p>
 * condition 实现线程交替打印
 */
public class CirclePrint2 {

  /**
   * 编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，3个线程交替打印1-100的整数，要求输出结果有序,
   * 样例Sample:
   * Thread1: 1
   * Thread2: 2
   * Thread3: 3
   * Thread1: 4
   * Thread2: 5
   * Thread3: 6
   * ....
   * Thread3: 99
   * Thread1: 100
   */

  Thread t1, t2, t3;

  @Test
  public void test() throws InterruptedException {
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    AtomicInteger num = new AtomicInteger(1);
    t1 = new Thread(() -> {

      try {
        lock.lock();
        while (num.intValue() < 100) {
          if (num.intValue() % 3 != 1) {
            conditionA.await();
          }

          System.out.println(Thread.currentThread().getName() + ":" + num.intValue());
          num.incrementAndGet();
          conditionB.signal();
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }, "thread1");

    t1.start();
    t2 = new Thread(() -> {
      try {
        lock.lock();
        while (num.intValue() < 100) {
          if (num.intValue() % 3 != 2) {

            conditionB.await();
          }
          System.out.println(Thread.currentThread().getName() + ":" + num.intValue());
          num.incrementAndGet();
          conditionC.signal();
        }


      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }, "thread2");

    t2.start();

    t3 = new Thread(() -> {
      try {
        lock.lock();

        while (num.intValue() < 100) {
          if (num.intValue() % 3 != 0) {

            conditionC.await();
          }
          System.out.println(Thread.currentThread().getName() + ":" + num.intValue());
          num.incrementAndGet();
          conditionA.signal();
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }, "thread3");


    t3.start();
    Thread.sleep(10000);

  }


}
