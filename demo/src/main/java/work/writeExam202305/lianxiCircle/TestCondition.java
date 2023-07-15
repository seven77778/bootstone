package work.writeExam202305.lianxiCircle;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lsh
 * @date 2023/6/19 11:59
 * condition.await 可以释放锁
 */
public class TestCondition {

  @Test
  public void testCondition() throws InterruptedException {
    ReentrantLock lock = new ReentrantLock();
    //创建新的条件变量
    Condition condition = lock.newCondition();


    Thread thread0 = new Thread(() -> {
      lock.lock();
      try {
        System.out.println("线程0获取锁");
        // sleep不会释放锁
        Thread.sleep(500);
        //进入休息室等待
        System.out.println("线程0释放锁，进入等待");
        condition.await();
        System.out.println("线程0被唤醒了");
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    });

    thread0.start();
    //叫醒
    Thread thread1 = new Thread(() -> {
      lock.lock();
      try {
        System.out.println("线程1获取锁");
        //唤醒
        condition.signal();
        System.out.println("线程1唤醒线程0");
      } finally {
        lock.unlock();
        System.out.println("线程1释放锁");
      }
    });

    thread1.start();
    Thread.sleep(10000);

//      thread0.join();

//      thread1.join();
  }
}
