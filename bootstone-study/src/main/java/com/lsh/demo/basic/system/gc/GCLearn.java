package com.lsh.demo.basic.system.gc;

/**
 * Created by lsh on 2019-04-25.
 *
 * 思考：垃圾回收，都回收哪些东西，一次完整的调用，从control到service中new的对象怎么处理
 *
 * jvm需要判断哪些东西是垃圾对吧 （这里的重点是两种算法：引用计数法和可达性分析法）
 * 然后开始垃圾回收啦：
 * 垃圾回收时，JVM会首先找到所有的GC Roots，这个过程叫做枚举根节点，这个过程需要暂停用户线程，也就是stop the world；
 * 然后再从GC Roots这些根节点向下搜索，可达的对象保留，不可达的便会回收掉。
 *
 * 什么是GC roots呢 -- 主要看 全局对象和执行上下文
 *
 * 全局对象：
 * 1.方法区静态属性引用的对象：全局对象的一种，Class对象本身很难被回收，回收的条件也是很苛刻，只要Class不被回收，静态成员不会被回收
 *
 * 2.方法区常量池引用的对象：全局对象，比如字符串常量池，常量初始化之后不会再次改变
 *
 * 思考：类会被回收吗，静态变量回收吗？ todo
 * 静态变量其实很少被回收吧
 *
 * 执行上下文：
 * 1.方法栈的栈帧本地变量表引用的对象：线程方法执行的时候，还没执行完，不应该回收
 * 2.被同步锁持有的对象，synchronize修饰的对象，锁没释放，就不回收
 *
 * 问：什么时候会stop the world？
 * 目前所有的新生代gc都是需要STW的
 *
 * Serial：单线程STW，复制算法
 * ParNew：多线程并行STW，复制算法
 * Parallel Scavange：多线程并行STW，吞吐量优先，复制算法
 * G1：多线程并发，可以精确控制STW时间，整理算法。
 *
 * cms是老年代，但是cms remark 也会stw
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
