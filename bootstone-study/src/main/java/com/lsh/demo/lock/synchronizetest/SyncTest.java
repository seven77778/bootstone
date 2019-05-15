package com.lsh.demo.lock.synchronizetest;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/8 16:42.
 *
 * @author lsh
 * @date 2018/11/08
 */
public class SyncTest {
    /**
     * test .class 文件中synchronized
     *
     * Compiled from "SyncTest.java"
     public class com.basic.lock.synchronizetest.SyncTest {
     public com.basic.lock.synchronizetest.SyncTest();
     Code:
     0: aload_0
     1: invokespecial #1                  // Method java/lang/Object."<init>":
     ()V
     4: return

     public void test();
     Code:
     0: aload_0
     1: dup
     2: astore_1
     3: monitorenter
     4: getstatic     #2                  // Field java/lang/System.out:Ljava/
     io/PrintStream;
     7: ldc           #3                  // String hello
     9: invokevirtual #4                  // Method java/io/PrintStream.printl
     n:(Ljava/lang/String;)V
     12: aload_1
     13: monitorexit
     14: goto          22
     17: astore_2
     18: aload_1
     19: monitorexit
     20: aload_2
     21: athrow
     22: return
     Exception table:
     from    to  target type
     4    14    17   any
     17    20    17   any
     }
     * */
    @Test
    public void test(){
        synchronized (this){
            System.out.println("hello");
        }
    }
}
