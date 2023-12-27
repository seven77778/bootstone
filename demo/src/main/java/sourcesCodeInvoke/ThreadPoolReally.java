package sourcesCodeInvoke;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author lsh
 * @date 2023/12/27 21:24
 * <p>
 * 线程池真的懂了吗
 * @see ThreadPoolExecutor#getTask()
 */
public class ThreadPoolReally {


  @Test
  public void test() throws InterruptedException {
    ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
      new LinkedBlockingDeque<Runnable>(1), threadFactory, new ThreadPoolExecutor.AbortPolicy());

    //poll 可以带超时时间
    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
   // linkedBlockingQueue.poll(5, TimeUnit.SECONDS);

    for (int i = 0; i < 10; i++) {
      int finalI = i;
      threadPoolExecutor.execute(new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
          System.out.println("this is " + finalI);

        }
      });
    }

    System.out.println(threadPoolExecutor.getActiveCount());
    Thread.sleep(10001111);

  }

}
