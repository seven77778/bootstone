package algorithm.leetcode.visible;

import akka.util.Unsafe;

import java.util.concurrent.locks.LockSupport;

/**
 * @author lsh
 * @date 2023/7/5 10:02
 * <p>
 * https://mp.weixin.qq.com/s/yS6fjvXxhMOO73XTT8SnXQ
 */
public class VisibleTest {


  //测试flag何时可见
  // -Djava.compiler=NONE，不加，一直死循环，加上，可见
  public static void main(String[] args) {
    Visible visible = new Visible();

    Thread thread = new Thread(() -> {
      while (!visible.flag) {
//        Thread.yield();
//        LockSupport.parkNanos(1); // yield，locksupport 也可以

        Unsafe.instance.loadFence();//内存屏障导致的回写主内存
      }

      System.out.println("change");
    });
//    thread.setDaemon(false);
    thread.start();

    Thread writer = new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      visible.flag = true;
    });

//    writer.setDaemon(false);
    writer.start();
  }

  static class Visible {
    public boolean flag;//加上volatile肯定可见，测试的是不加volatile
  }


}
