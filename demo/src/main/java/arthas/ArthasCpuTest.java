package arthas;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lsh
 * @date 2023/5/27 17:07
 */
public class ArthasCpuTest {

    @Test
    public void test() {

        new Thread( () -> {
            while (true) {
                String str = UUID.randomUUID().toString().replaceAll("-", "");
            }
        },"lsh cpu").start();

        new Thread( () -> {
            while (true) {
                String str = UUID.randomUUID().toString().replaceAll("-", "");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"lsh cpu2").start();

        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
