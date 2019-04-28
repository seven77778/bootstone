package com.lsh.demo.bootstone.study.system.gc;

/**
 * Created by lsh on 2019-04-26.
 *
 * 不是所有的oom都会导致进程崩溃
 */
public class OomLearn {

    /*
        1. java -jar service.jar -Xmx50m -Xms50m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:\oom

        后面跟的jvm参数不生效 Java VisualVM 显示的堆内存 打到250m才oom

        解 参数需要放在java 和 -jar之间
        java  -Xmx50m -Xms50m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:\oom -jar service.jar

     */

}
