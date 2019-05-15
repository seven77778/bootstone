package com.lsh.demo.basic.system.memoryhigh;

import org.junit.Test;
import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * Created by lsh on 2018/11/27 16:33
 *
 * definition：测试堆外内存不生效的问题
 *
 * -Xmx100m -Xms100m -verbose:gc -XX:+PrintGCDetails -XX:MaxDirectMemorySize=50m -XX:+DisableExplicitGC
 * 关键是-XX:+DisableExplicitGC，如果使用堆外内存，禁用会导致 java.lang.OutOfMemoryError: Direct buffer memory
 *
 *堆外内存的好处是：

 (1)可以扩展至更大的内存空间。比如超过1TB甚至比主存还大的空间;

 (2)理论上能减少GC暂停时间;

 (3)可以在进程间共享，减少JVM间的对象复制，使得JVM的分割部署更容易实现;

 (4)它的持久化存储可以支持快速重启，同时还能够在测试环境中重现生产数据
 *
 */
public class DirectByteBufferTest {

    @Test
    public void test() throws Exception {
        while(true) {
            Thread.sleep(50);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1);
        byte b = 123;
        byteBuffer.put(1,b);
        byte[] by = new byte[]{1,2};
        byteBuffer.put(by);
        System.out.println(byteBuffer.get(1));
    }

    @Test
    public void test2(){
        ByteBuffer[] b = new ByteBuffer[]{};
        //ByteBuffer byteBuffer = new ByteBuffer() {}
        System.out.println(VM.maxDirectMemory());
    }

    /**
     * -XX:NativeMemoryTracking=detail
     */
    @Test
    public void test3(){
        System.out.println();
    }
}
