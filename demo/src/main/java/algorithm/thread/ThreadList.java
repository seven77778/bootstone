package algorithm.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lsh
 * @date 2023/6/21 0:51
 * <p>
 * 一道线程安全 面试题
 */
public class ThreadList {

  @Test
  public void test() throws InterruptedException {
    List<Integer> list = new ArrayList<>();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        list.add(i);
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        list.add(i);
      }
    });
    /**
     * synchronize 是可以，必须带着t1.join方法，
     */
    synchronized (ThreadList.class) {

      t1.start();
      t1.join(); //加了join，其他线程会等待t1执行完毕
    }
    synchronized (ThreadList.class) {
      t2.join();
      t2.start();
    }
    System.out.println(list.size());
  }


  @Test
  public void test2() throws InterruptedException {
    List<Integer> list = new ArrayList<>();
    ReentrantLock lock = new ReentrantLock();
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        list.add(i);
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        list.add(i);
      }
    });


    /**
     * 只要是锁都可以
     */

    lock.lock();
    t1.start();
    t1.join(); //加了join，其他线程会等待t1执行完毕
    lock.unlock();

    t2.start();
    t2.join();
    System.out.println(list.size());
  }
}
