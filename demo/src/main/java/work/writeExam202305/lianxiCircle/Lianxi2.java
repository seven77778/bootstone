package work.writeExam202305.lianxiCircle;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lsh
 * @date 2023/5/16 18:24
 */
public class Lianxi2 {

    private List<Thread> list = Lists.newArrayList();
    private int max = 100;
    private int threadCount = 0;
    private int numStart = 0;
    private int size = 3;

    @Test
    public void test() throws InterruptedException {
        for (int i = 1; i <= size; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    LockSupport.park();
                    if (numStart < max) {
                        numStart++;
                        System.out.println(Thread.currentThread().getName() + ":" + numStart);
                    }else {
                        break;
                    }
                    LockSupport.unpark(list.get(++threadCount % list.size()));
                }
            });
            t.setName(String.format("Thread%d", i));
            list.add(t);

        }

        list.forEach(Thread::start);
        LockSupport.unpark(list.get(0));
        Thread.sleep(1000);
    }
}
