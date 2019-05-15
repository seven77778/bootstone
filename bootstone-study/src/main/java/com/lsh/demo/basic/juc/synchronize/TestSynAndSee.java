package com.lsh.demo.basic.juc.synchronize;

/**
 * Created by lsh on 2019-05-10.
 */
public class TestSynAndSee {
    int result;

    public int getResult() {
        return result;
    }

    public synchronized void setResult(int result) {
        this.result = result;
    }

    public static void main(String[] args) {
        TestSynAndSee threadSafeCache = new TestSynAndSee();

        for (int i = 0; i < 8; i++) {
            System.out.println("第" + i + "次");
            new Thread(() -> {
                int x = 0;
                while (threadSafeCache.getResult() < 100) {
                    x++;
                }
                System.out.println(x);
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadSafeCache.setResult(200);
    }
}
