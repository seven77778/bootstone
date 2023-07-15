package algorithm.leetcode.lock;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/7/3 19:00
 * <p>
 * 用 wait notify实现线程交替打印
 */
public class MyNotifyPrintTest {


  //join 不是交替打印。。
  @Test
  public void test() throws Exception {
    Thread t1 = new Thread(() -> {
      synchronized (this) {
        for (int i = 0; i < 5; i++) {
          this.notify();
          System.out.println(Thread.currentThread().getName() + ":" + i);
          try {
            this.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }, "A");

    //第一次运行，t1拿到锁，打印A-1，wait释放锁，t2拿到锁，notify通知t1，但也要等wait释放锁之后，t1才能拿到锁执行

    Thread t2 = new Thread(() -> {
      synchronized (this) {
        for (int i = 0; i < 5; i++) {
          this.notify();
          System.out.println(Thread.currentThread().getName() + ":" + i);
          try {
            this.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }, "B");
    t1.start();
    t2.start();
    Thread.sleep(1000);
  }



  /*  实现三个线程交替打印  fixme */





}
