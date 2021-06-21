package com.lsh.demo.basic.system.gcabout;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.UnmarshallerImpl;
import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

/**
 * Created by lsh on 2018/11/23 20:43
 *
 * 显示调用 finalize()
 *
 * -XX:+PrintGCDetails -Xmx50m -Xms50m -XX:MetaspaceSize=50m -XX:+UseParallelGC
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 * After creating 1100000 objects, 802425 are still alive.
 *
 * 为什么会出现溢出？因为Finalizer线程和主线程相比它的优先级要低。这意味着分配给它的CPU时间更少，因此它的处理速度没法赶上新对象创建的速度。
 * 这就是问题的根源——对象创建的速度要比Finalizer线程调用finalize()结束它们的速度要快，这导致最后堆中所有可用的空间都被耗尽了，结果就是OOM
 *
 * Finalizer线程是个单一职责的线程。这个线程会不停的循环等待java.lang.ref.Finalizer.ReferenceQueue 中的新增对象。
 * 一旦Finalizer线程发现队列中出现了新的对象，它会弹出该对象，调用它的finalize()方法，
 * 将该引用从Finalizer类中移除，因此下次GC再执行的时候，这个Finalizer实例以及它引用的那个对象就可以回垃圾回收掉了
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 更换cms之后：-XX:+PrintGCDetails -Xmx20m -Xms20m -Xmn10m -XX:MetaspaceSize=20m -XX:+UseConcMarkSweepGC
 * After creating 29000000 objects, 314106 are still alive.
 * 异常也不同：java.lang.OutOfMemoryError: Java heap space
 *
 * 疑问：Finalizer 是无限长度队列？
 */
public class FinalizeTest {

    static LongAdder aliveCount = new LongAdder();

    @Override
    protected void finalize() throws Throwable {
        FinalizeTest.aliveCount.decrement();
    }

    public FinalizeTest() {
        aliveCount.increment();
    }

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
     at java.lang.ref.Finalizer.register(Finalizer.java:87)
     at java.lang.Object.<init>(Object.java:37)
     at com.basic.gc.gcabout.FinalizeTest.<init>(FinalizeTest.java:38)
     at com.basic.gc.gcabout.FinalizeTest.main(FinalizeTest.java:44)
     Heap dump file created [33250082 bytes in 0.239 secs]
     Heap
     *
     *-XX:+PrintGCDetails -Xmx20m -Xms20m -Xmn10m -XX:MetaspaceSize=20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\oomdum
     */
    public static void main(String args[]) throws Exception {
        for (int i = 0;; i++) {
            FinalizeTest f = new FinalizeTest();
            if ((i % 100_000) == 0) {
                Thread.sleep(10);
                System.out.format("After creating %d objects, %d are still alive.%n", new Object[] {i, FinalizeTest.aliveCount.intValue() });
            }
        }
    }

    /**
     * -XX:+PrintCommandLineFlags
     */
    @Test
    public void test(){
        FinalizeTest f = new FinalizeTest();
        f=null;
        System.gc();
    }

    /**
     * UnmarshallerImpl
     * 情景复现，测试oom
     * -XX:+PrintGCDetails -Xmx20m -Xms20m -Xmn10m -XX:MetaspaceSize=20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\oomdum
     * 结果：
     * 被类加载器"bootstrap class loader"加载的11,689个"java.lang.ref.Finalizer"实例占了12,162,280 (85.90%)字节.

     关键字
     java.lang.ref.Finalizer
     */
    @Test
    public void test2() throws Exception {
        for(int i=0;;i++) {
            Thread.sleep(50);
            UnmarshallerImpl unmarshaller = new UnmarshallerImpl(null, null);
            if ((i % 100_000) == 0) {
                System.out.format("After creating %d objects.%n", new Object[] {i });
            }
        }
    }
    /**
     * -verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:+UnlockDiagnosticVMOptions  -XX:+PrintNMTStatistics -XX:NativeMemoryTracking=summary
     * 测试堆外内存
     */
}
