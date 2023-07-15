package algorithm.leetcode.thread;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lsh
 * @date 2023/7/4 9:47
 * <p>
 * 输入：n = 1
 * 输出："foobar"
 * 解释：这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 */
public class FooBar {

  private int n;

  public FooBar() {
  }


  /***************************syn+控制位************************************************/
  volatile boolean flag = true;

  public void foo2(Runnable printFoo) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      // printFoo.run() outputs "foo". Do not change or remove this line.
      synchronized (this) {
        while (!flag) {
          this.wait();
        }
        printFoo.run();
        System.out.println("foo");
        flag = false;
        this.notifyAll();//notifyall很关键，不造成阻塞的关键
      }
    }

  }

  public void bar2(Runnable printBar) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      // printBar.run() outputs "bar". Do not change or remove this line.
      synchronized (this) {
        while (flag) {
          this.wait();
        }
        printBar.run();
        System.out.println("bar");
        flag = true;
        this.notifyAll();
      }
    }
  }

  /**************************试试CAS***************************************/
  volatile int temp = 1;

  public void foo3(Runnable printFoo) throws InterruptedException {
    for (int i = 0; i < n; ) {
      // printFoo.run() outputs "foo". Do not change or remove this line.
      if (temp % 2 != 0) {
        printFoo.run();
        System.out.print("foo");
        i++;
        temp++;
      } else {
        Thread.yield();//没有yield会超时
      }
    }

  }

  public void bar3(Runnable printBar) throws InterruptedException {
    for (int i = 0; i < n; ) {
      // printBar.run() outputs "bar". Do not change or remove this line.
      if (temp % 2 == 0) {
        printBar.run();
        i++;
        System.out.print("bar");
        temp++;
      } else {
        Thread.yield();
      }
    }
  }


  /**************************BlockingQueue***************************************/
  LinkedBlockingQueue<Integer> foo = new LinkedBlockingQueue<>(1);
  LinkedBlockingQueue<Integer> bar = new LinkedBlockingQueue<>(1);

  public void foo(Runnable printFoo) throws InterruptedException {


    for (int i = 0; i < n; i++) {
      // printFoo.run() outputs "foo". Do not change or remove this line.
      bar.put(i);// bar没put之前，take都是阻塞。bar-put后，foo.take还无法执行，要等foot.put后才能执行
      printFoo.run();
      System.out.print("foo");
      foo.put(i);
    }

  }

  public void bar(Runnable printBar) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      // printBar.run() outputs "bar". Do not change or remove this line.
      foo.take();
      printBar.run();
      System.out.print("bar");
      bar.take();
    }
  }

  /***********************Test**************************************/

  //还是得在main中测试，如果有锁没释放，线程会一直阻塞
  public static void main(String[] args) throws InterruptedException {
    FooBar fooBar = new FooBar();
    fooBar.n = 5;

    new Thread(() -> {
      try {
        fooBar.bar(new Runnable() {
          @Override
          public void run() {
          }
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();


    new Thread(() -> {
      try {
        fooBar.foo(() -> {
        });
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    Thread.sleep(111);

  }


}
