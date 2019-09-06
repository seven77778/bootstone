package com.lsh.demo.basic.thread;

/**
 * Created by lsh on 2019-04-24.
 *
 * maxThreads 【请求处理线程的最大数量】 200
 * 【Tomcat通过使用比CPU核心数量多得多的线程数，可以使CPU忙碌起来，大大提高CPU的利用率】
 * (int) The max number of active threads in this pool, default is 200
 *
 * minSpareThreads
 * (int) The minimum number of threads always kept alive, default is 25
 *
 * maxQueueSize
 * (int) The maximum number of runnable tasks that can queue up awaiting execution before we reject them.
 * Default value is Integer.MAX_VALUE
 *
 * maxIdleTime
 * (int) The number of milliseconds before an idle threadlocal shutsdown, unless the number of active threads are less or equal to minSpareThreads.
 * Default value is 60000(1 minute)
 *
 *
 */
public class TomcatTest {


    /* 疑问
    1. springboot 通过main启动，是什么方式

    2.java -jar 是 tomcat启动吗



     */
}
