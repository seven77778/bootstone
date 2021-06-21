package com.lsh.demo.basic.system.gc;

/**
 * 参考 ：http://ifeve.com/useful-jvm-flags-part-5-young-generation-garbage-collection/
 * Created by lsh on 2019-04-25.
 *
 * java程序最大可能占用内存 = Xmx指定的最大堆内存大小 + 最大活跃线程数量 * Xss指定的每个线程栈内存大小
 *      + XX:MaxDirectMemorySize指定的最大直接内存大小 + MetaSpace 大小
 *
 *      2020年7月2日 add -- CMS-concurrent-mark 耗时最长
 *
 *      ParallelGCThreads是年轻代的并行收集线程数，CMSScavengeBeforeRemark，
 *      强制remark之前开始一次minor gc，减少remark的暂停时间， 因为发现你们 remark时间最长
 *
 *      也调大新生代试试吧， remark 期间，新生代晋升到老年代，或者有大对象直接到老年代，都会增加耗时
 *
 *      有二方包内存泄漏
 */

/**
 * 2020-06-30T08:35:57.081+0800: 2890089.400: [CMS-concurrent-mark-start]
 * 2020-06-30T08:36:01.175+0800: 2890093.494: [CMS-concurrent-mark: 4.009/4.094 secs] [Times: user=5.12 sys=0.00, real=4.09 secs]
 * 2020-06-30T08:36:01.175+0800: 2890093.494: [CMS-concurrent-preclean-start]
 * 2020-06-30T08:36:01.343+0800: 2890093.662: [CMS-concurrent-preclean: 0.109/0.168 secs] [Times: user=0.19 sys=0.00, real=0.17 secs]
 * 2020-06-30T08:36:01.344+0800: 2890093.663: [CMS-concurrent-abortable-preclean-start]
 * 2020-06-30T08:36:01.344+0800: 2890093.663: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 2020-06-30T08:36:01.349+0800: 2890093.668: [GC (CMS Final Remark) [YG occupancy: 1177162 K (1922432 K)]2020-06-30T08:36:01.349+0800: 2890093.668:
 * [Rescan (parallel) , 0.4430175 secs]2020-06-30T08:36:01.792+0800: 2890094.111: [weak refs processing, 0.0000819 secs]2020-06-30T08:36:01.792+0800: 2890094.111:
 * [class unloading, 0.1558433 secs]2020-06-30T08:36:01.948+0800: 2890094.267: [scrub symbol table, 0.0719194 secs]2020-06-30T08:36:02.020+0800: 2890094.339:
 * [scrub string table, 0.0076511 secs][
 * 1 CMS-remark: 1768434K(2097152K)] 2945597K(4019584K), 0.6846606 secs] [Times: user=1.88 sys=0.00, real=0.68 secs]
 * 2020-06-30T08:36:02.034+0800: 2890094.353: [CMS-concurrent-sweep-start]
 * 2020-06-30T08:36:02.904+0800: 2890095.224: [CMS-concurrent-sweep: 0.870/0.870 secs] [Times: user=1.04 sys=0.00, real=0.87 secs]
 * 2020-06-30T08:36:02.905+0800: 2890095.224: [CMS-concurrent-reset-start]
 * 2020-06-30T08:36:02.908+0800: 2890095.227: [CMS-concurrent-reset: 0.003/0.003 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
 * 2020-06-30T08:36:04.913+0800: 2890097.232: [GC (CMS Initial Mark) [1 CMS-initial-mark: 1768391K(2097152K)] 2970936K(4019584K), 0.4470559 secs] [Times: user=1.64 sys=0.00, real=0.45 secs]
 */
public class CMSLearn {
    /**
     * 1.cms是old gc，还是young gc，还是别的啥
     * -- cms只作用于 老年代
     * 看日志，伴随着cms的young gc 是 ParNew
     *
     * 2.缺点：
     * 默认是没有碎片处理的，时间长了以后，碎片化严重
     */

    public static void main(String[] args) {
        System.gc();
        System.out.println();
    }

    /*

    重点是 -XX:NewRatio=2，新生代:老生代 = 1:2
    新生代存在的唯一理由是优化垃圾回收(GC)的性能,因为很多对象的生成时间都很短，
    而且新生的对象也很少去引用 <生存时间长> 的对象，所以弄了一个区域。

    新生代有分为3块，较大的eden，两个较小的 survivor 区，年轻代GC ，对象在2个Survivor中来回复制
    到达存活年龄以后，转移到老年代

    注意 1 如果新生代过小，会导致新生对象很快就晋升到老年代中，在老年代中对象很难被回收
    2 如果新生代过大，会发生过多的复制过程
    3 一般不允许新生代比老年代还大,最坏的情况就是 所有对象晋升到 老年代
    新生代为什么不能比老年代大？？ todo --2 复制
    4 如果针对新生代,同时定义绝对值和相对值,绝对值将起作用

    */

    /*

    -XX:+PrintTenuringDistribution 指定JVM 在每次新生代GC时，输出幸存区中对象的年龄分布

     */



    /*
    CSM执行过程：


    3.CMS缺点
    CMS回收器采用的基础算法是Mark-Sweep。CMS不会整理、压缩堆空间。
    这样就会有一个问题：经过CMS收集的堆会产生空间碎片。
    CMS不对堆空间整理压缩节约了垃圾回收的停顿时间，但也带来的堆空间的浪费。
    为了解决堆空间浪费问题，CMS回收器不再采用简单的指针指向一块可用堆空间来为下次对象分配使用。
    而是把一些未分配的空间汇总成一个列表，当JVM分配对象空间的时候，会搜索这个列表找到足够大的空间来hold住这个对象。
    需要更多的CPU资源。从上面的图可以看到，为了让应用程序不停顿，CMS线程和应用程序线程并发执行，
    这样就需要有更多的CPU，单纯靠线程切换是不靠谱的。并且，重新标记阶段，为空保证STW快速完成，
    也要用到更多的甚至所有的CPU资源。
    当然，多核多CPU也是未来的趋势！

    CMS的另一个缺点是它需要更大的堆空间。因为CMS标记阶段应用程序的线程还是在执行的，
    那么就会有堆空间继续分配的情况，为了保证在CMS回收完堆之前还有空间分配给正在运行的应用程序，必须预留一部分空间。
    也就是说，CMS不会在老年代满的时候才开始收集。相反，它会尝试更早的开始收集，已避免上面提到的情况：
    在回收完成之前，堆没有足够空间分配！默认当老年代使用68%的时候，CMS就开始行动了。

    – XX:CMSInitiatingOccupancyFraction =n 来设置这个阀值。
    总得来说，CMS回收器减少了回收的停顿时间，但是降低了堆空间的利用率。

    4.啥时候用CMS
    如果你的应用程序对停顿比较敏感，并且在应用程序运行的时候可以提供更大的内存和更多的CPU(也就是硬件牛逼)，
    那么使用CMS来收集会给你带来好处。还有，如果在JVM中，有相对较多存活时间较长的对象(老年代比较大)会更适合使用CMS。

     5. 日常请求超时，排查到刚好发生full gc，耗时6秒，【class unloading,5.5285249 secs】
     同时，swap很高,swap交换就用到硬盘了，
     GC慢机器 cat /proc/sys/vm/swappiness 60
     GC正常机器 cat /proc/sys/vm/swappiness 1

     -- https://blogs.oracle.com/poonam/long-class-unloading-pauses-with-jdk8

     在这种情况下，问题似乎也不在于 JVM，或者更具体地说，不是与类卸载步骤有关，而是与 GC 线程被阻塞有关。
     事实证明，类卸载阶段导致在系统级别发生了大量分页活动，这占用了 GC 线程的 CPU 时间。

    JVM 中从 JDK7 到 JDK8 的变化是在 JDK8 中我们没有 PermGen。相反，我们有 MetaSpace，它是在本机内存空间之外分配的。
正如我之前提到的，用户运行时启用了大页面。在 JDK7 中，通过使用选项 -XX:+UseLargePages，
我们可以为 Java Heap 和 PermGen 启用大页面。但是对于jdk8，类元数据存储在MetaSpace（原生空间）中，
当我们使用+UseLargePages时，默认情况下我们不会为MetaSpace启用大页面。

     */

}
