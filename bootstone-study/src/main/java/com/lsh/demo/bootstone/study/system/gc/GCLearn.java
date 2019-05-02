package com.lsh.demo.bootstone.study.system.gc;

/**
 * Created by lsh on 2019-04-25.
 *
 * 制卡 需加 system.gc 否则进程崩溃
 */
public class GCLearn {

    /*
    java程序最大可能占用内存 = Xmx指定的最大堆内存大小+ 最大活跃线程数量*Xss指定的每个线程栈内存大小
     + XX:MaxDirectMemorySize指定的最大直接内存大小 + MetaSpace 大小


     */


    /*
    ```
    总内存 = 堆 + 代码缓存 + Metaspace + 符号表 +
            其他 JVM 结构 + 线程堆栈 +
            Direct Buffer + 映射文件 +
            Native Library + Malloc 开销 + ...
    ```

     */

    /**
        MappedByteBuffer  public
        DirectByteBuffer  not public

     */
    public static void main(String[] args) {


    }

}
