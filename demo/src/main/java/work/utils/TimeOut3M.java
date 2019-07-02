package work.utils;

import java.util.concurrent.*;

/**
 * Created by lsh on 2019-07-02.
 *
 * 3秒超时逻辑
 *
 */
public class TimeOut3M<V> implements Callable<V> {

    private final Callable<V> callable;
    private final V timeoutV;
    private final long timeout;

    /**
     * 构造一个 TimeoutCallable
     *
     * @param callable 要运行的 Callable
     * @param timeout Callable 的最大运行时间
     * @param timeoutV Callable 超时的返回结果
     */
    public TimeOut3M(Callable<V> callable, long timeout, V timeoutV) {
        this.timeout = timeout;
        this.callable = callable;
        this.timeoutV = timeoutV;
    }

    @Override
    public V call() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<V> future = executor.submit(callable);

        V v = null;
        try {
            v = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException ex) {
            System.out.println("Callble 超时");
        }

        executor.shutdownNow(); // 给线程池中所有正在运行的线程发送 中断 信号

        return v != null ? v : timeoutV;
    }


    //test
    public static void main(String[] args) throws Exception {

        Callable<Integer> callable = () -> {
            int N = 4;
            int sum = 0;
            for (int i = 0; i < N; i++) {
                // Thread.sleep 方法是可以响应中断的，
                // 如果你的代码需要“超时则线程结束”的效果，那么你的代码也应该要能够响应中断
                Thread.sleep(1000);
                sum += i;
            }
            return sum;
        };
        // 代码2, 代码2 运行的最长时间为 timeout
        int timeout = 5000;
        Integer timeoutValue = -1;
        TimeOut3M<Integer> timeoutCallable = new TimeOut3M<>(callable, timeout, timeoutValue);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(timeoutCallable);

        Integer result = future.get();
        executor.shutdown();
        // end 代码2

        // 代码3
        if (timeoutValue.equals(result)) {
            System.out.println("--任务超时--");
        } else {
            System.out.println("任务结果：" + result);
        }
        // end 代码3
    }

}
