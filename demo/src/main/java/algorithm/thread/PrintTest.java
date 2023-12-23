package algorithm.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lsh
 * @date 2023/6/20 20:16
 * todo 复习
 */
public class PrintTest {

  int start = 0;
  ArrayList<Thread> list = new ArrayList<>();

  @Test
  public void test() throws InterruptedException {

    int num = 10;


    for (int i = 0; i < 3; i++) {
      int finalI = i + 1;
      Thread t = new Thread(() -> {
        while (true) {
          LockSupport.park();
          if (start < num) {
            start++;
            System.out.println(Thread.currentThread().getName() + ":" + start);
          } else {
            break;
          }
          LockSupport.unpark(list.get(finalI % 3));
        }
      }, String.format("Thread---%d",finalI));

      list.add(t);
    }

    list.forEach(x -> x.start());
    LockSupport.unpark(list.get(0));

    Thread.sleep(1000);
    System.out.println(123);

  }

}
