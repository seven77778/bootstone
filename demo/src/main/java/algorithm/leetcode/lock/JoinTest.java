package algorithm.leetcode.lock;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/7/3 19:02
 */
public class JoinTest {

  @Test
  public void test() throws InterruptedException {
    System.out.println("start -- main");

    Thread t1 = new Thread(()->{

      for(int i=0;i<5;i++){
        System.out.println("A");
      }
    });

    Thread t2 = new Thread(()->{
      for(int i=0;i<5;i++){
        System.out.println("B");
      }
    });

    Thread t3 = new Thread(()->{
      for(int i=0;i<5;i++){
        System.out.println("C");
      }
    });


    t1.start();
//    t1.join();//加上这句，结果变得有序，其实是因为t1.join让面陷入等待
    t2.start();
    t2.join();//如果放在这里，t1t2无序，但是t3要等t2执行完

    t3.start();
//    Thread.currentThread().join();
//    t2.join();

    System.out.println(111);


  }
}
