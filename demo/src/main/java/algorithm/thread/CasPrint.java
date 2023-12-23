package algorithm.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lsh
 * @date 2023/8/1 21:28
 */
public class CasPrint {


  /**
   * 不按顺序
   *
   * @throws InterruptedException
   */
  @Test
  public void testCasList() throws InterruptedException {
    int num = 10;
    AtomicInteger atomicInteger = new AtomicInteger();
    List<Thread> list = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      int finalI = i;
      Thread t = new Thread(() -> {
        while (atomicInteger.get()<num) {
          if (atomicInteger.get() % 3 == finalI) {
            System.out.println(Thread.currentThread().getName() +atomicInteger.get() );
            atomicInteger.incrementAndGet();
          } else {
            Thread.yield();
          }
        }
      }, "name-" + i + ":");
      list.add(t);
    }
    list.forEach(x -> x.start());
    list.get(0).join();
  }

  @Test
  public void testCasList2() throws InterruptedException {
    int num = 9;
    AtomicInteger atomicInteger = new AtomicInteger(0);
    List<Thread> list = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      int finalI = i;
      Thread t = new Thread(() -> {
        while (true) {
          if (atomicInteger.get() > num) {
            return;
          }
          if (atomicInteger.get() % 3 == finalI) {

            System.out.println(Thread.currentThread().getName() + atomicInteger.get());
            LockSupport.parkNanos(1);
            atomicInteger.incrementAndGet();

          } else {
            Thread.yield();
          }
        }
      }, "name-" + (i + 1) + ":");
      list.add(t);
    }
    list.forEach(x -> x.start());
    list.get(0).join();
    list.get(1).join();
    list.get(2).join();
  }

  private static final int MAX_COUNT = 10;
  private static final int THREAD_COUNT = 3;
  private AtomicInteger turn = new AtomicInteger(0);

  @Test
  public void main() {
    Thread[] threads = new Thread[THREAD_COUNT];

    for (int i = 0; i < THREAD_COUNT; i++) {
      final int threadId = i;
      threads[i] = new Thread(() -> {
        while (true) {
          int currentTurn = turn.get();
          if (currentTurn > MAX_COUNT) {
            break;
          }

          if (currentTurn % THREAD_COUNT == threadId) {
            System.out.println(Thread.currentThread().getName() + ": " + currentTurn);
//              turn.compareAndSet(currentTurn, currentTurn + 1);
            turn.incrementAndGet();
          }
        }
      });
      threads[i].start();
    }

    for (int i = 0; i < THREAD_COUNT; i++) {
      try {
        //TODO join 用法
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


}
