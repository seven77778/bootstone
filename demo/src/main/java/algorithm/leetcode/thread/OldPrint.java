package algorithm.leetcode.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lsh
 * @date 2023/8/15 20:56
 */
public class OldPrint {
  int num = 0;
  List<Thread> list = new ArrayList<>();

  @Test
  public void test3(){
    int[] arr = new int[]{3,5,6,2,4,7,78,2,446,99,0};

    PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2-o1;
      }
    });
    for(int i:arr){
      if(!priorityQueue.isEmpty() && priorityQueue.size()>2){
        if(priorityQueue.peek()>i){
          priorityQueue.poll();
          priorityQueue.offer(i);
        }
      }else {
        priorityQueue.offer(i);
      }
    }
    System.out.println(priorityQueue);
  }

  @Test
  public void test() {

    for (int i = 0; i < 3; i++) {
      int finalI = i;
      Thread t = new Thread(() -> {
        while (num < 10) {
          LockSupport.park();
          if (num > 10) {
            break;
          }
          System.out.println(Thread.currentThread().getName() + "-" + num++);

          LockSupport.unpark(list.get((finalI + 1) % 3));
        }

      }, "name:" + i);
      list.add(t);
    }


    list.forEach(Thread::start);
    LockSupport.unpark(list.get(0));

    list.forEach(x -> {
      try {
        x.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  @Test
  public void test2() throws InterruptedException {
    Semaphore semaphoreA = new Semaphore(1);
    Semaphore semaphoreB = new Semaphore(0);
    Semaphore semaphoreC = new Semaphore(0);

    Thread t1 = new Thread(() -> {
      while (num<10){
        try {
          semaphoreA.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if(num>10){
          break;
        }
        System.out.println(Thread.currentThread().getName() + "-" + num++);
        semaphoreB.release();
      }

    }, "A");

    Thread t2 = new Thread(() -> {
      while (num<10){
        try {
          semaphoreB.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if(num>10){
          break;
        }
        System.out.println(Thread.currentThread().getName() + "-" + num++);
        semaphoreC.release();
      }

    }, "B");

    Thread t3 = new Thread(() -> {
      while (num<10){
        try {
          semaphoreC.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if(num>10){
          break;
        }
        System.out.println(Thread.currentThread().getName() + "-" + num++);
        semaphoreA.release();
      }

    }, "C");

    t1.start();
    t2.start();
    t3.start();

    t1.join();
    t2.join();
    t3.join();

  }


}
