package algorithm.leetcode.thread;

/**
 * @author lsh
 * @date 2023/7/5 11:31
 */
public class DiningPhilosophers {


  public DiningPhilosophers() {

  }

  // call the run() method of any runnable to execute its code
  public void wantsToEat(int philosopher,
                         Runnable pickLeftFork,
                         Runnable pickRightFork,
                         Runnable eat,
                         Runnable putLeftFork,
                         Runnable putRightFork) throws InterruptedException {

  }

  public static void main(String[] args) throws Exception{

    DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
    Thread t1 = new Thread();
    diningPhilosophers.wantsToEat(1,t1,t1,t1,t1,t1);

  }


}
