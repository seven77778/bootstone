package sourcesCodeInvoke;

import javafx.util.Pair;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lsh
 * @date 2023/12/23 7:46
 * <p>
 * sentinel 中排序等待算法 在实际中的应用
 * @see com.alibaba.csp.sentinel.slots.block.flow.controller.RateLimiterController
 */
public class SentinelRateLimterController {

  /**
   * canPass 中计算时间，直接Thread.sleep 进行排队
   */
  @Test
  public void test() throws InterruptedException {
    Thread t1 = new Thread(() -> {
      LockSupport.park();
      System.out.println(111);
    });

    Thread t2 = new Thread(() -> {
      LockSupport.park();
      System.out.println(222);
    });

    Thread t3 = new Thread(() -> {
      LockSupport.park();
      System.out.println(333);
    });
    t1.start();
    t2.start();
    t3.start();
    System.out.println("线程排队等待中……");

    LinkedBlockingQueue<Pair<String, Thread>> linkedBlockingQueue = new LinkedBlockingQueue<>();

    linkedBlockingQueue.add(new Pair<>("t1", t1));
    linkedBlockingQueue.add(new Pair<>("t2", t2));
    linkedBlockingQueue.add(new Pair<>("t3", t3));

    //排队的任务放入queue中，根据一定条件进行唤醒
    while (true) {
      if (!linkedBlockingQueue.isEmpty()) {
        Pair<String, Thread> pair = linkedBlockingQueue.poll();
        System.out.println("唤醒：" + pair.getKey());
        LockSupport.unpark(pair.getValue());
      } else {
        System.out.println("end");
        break;
      }
      Thread.sleep(1000);
    }

  }


}
