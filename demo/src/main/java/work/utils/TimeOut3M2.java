package work.utils;

import java.util.concurrent.*;

/**
 * Created by lsh on 2019-07-02.
 *
 * 3秒超时逻辑 OK todo learn
 *
 *
 *
 */
public class TimeOut3M2 {

    public static void main(String[] args) {

        final ExecutorService exec = Executors.newFixedThreadPool(10);

        Callable<String> call = () -> {
            //开始执行耗时操作
            Thread.sleep(1000 * 5);//业务需要处理5秒，但是24行设置超时时间为3秒，3秒直接超时
            return "线程执行完成.";
        };


        long time = System.currentTimeMillis();
        try {
            Future<String> future = exec.submit(call);
            String obj = future.get(1000 * 6, TimeUnit.MILLISECONDS); //任务处理超时时间设为 1 秒
            System.out.println("任务成功返回:" + obj);
        } catch (TimeoutException ex) {
            System.out.println("处理超时啦....");
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println("处理失败.");
            e.printStackTrace();
        }
        // 关闭线程池
        exec.shutdown() ;
        System.out.println("耗时： "  + (System.currentTimeMillis() - time));
    }

}
