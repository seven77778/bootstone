package com.lsh.demo.basic.system.gcabout;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/1.
 *
 * @author lsh
 * @date 2018/11/01
 *
 * 方法一：jinfo -flag CMSInitiatingOccupancyFraction 进程id
-XX:CMSInitiatingOccupancyFraction=-1

 方法二：JVM 启动的时候使用 -XX:+PrintFlagsFinal 来显示所有可配置参数的信息，然后找到 CMSTriggerRatio 和 MinHeapFreeRatio
然后计算 CMSInitiatingOccupancyFraction 的公式为：

CMSInitiatingOccupancyFraction = (100 - MinHeapFreeRatio) + (CMSTriggerRatio * MinHeapFreeRatio / 100)
 */
public class PringJvmSettings {
    /**
     * -Xmn 年轻代大小
     *
     * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails  -XX:MaxTenuringThreshold=1
     * */
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * MaxTenuringThreshold 进入老年代的年龄
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails  -XX:MaxTenuringThreshold=1
     * */

    /**
     * 设置为8,则两个Survivor区与一个Eden区的比值为2:8,一个Survivor区占整个年轻代的1/10
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails  -XX:SurvivorRatio=8
     * */
    @Test
    public  void test() {
        byte[] allocation1, allocation2, allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];//出现一次young GC
        // 新生代一共9m，eden已经占用6m，无法分配4m
    }

    /**
     * test full GC
     * -Xloggc:D:\gclog.txt
     * */
    @Test
    public void test2(){
        {
            byte[] b = new byte[20 * 1024 * 1024];
        }
        System.gc();
    }


}
