package com.lsh.demo.basic.system.jvm;

import org.junit.Test;

/**
 * Created by lsh on 2018/11/8 19:45.
 *
 * @author lsh
 * @date 2018/11/08
 *
 * 1.开启逃逸分析，分析GC有何区别
 * -XX:+DoEscapeAnalysis
 *
 * 测试参数：-server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC
 *
 * 2.逃逸分析
 * 开启 stringbuffer 耗时减少 -- 参见《程序性能优化》 p213
 *
 * 尽管目前逃逸分析的技术不是很成熟，但它却是即时编译器优化技术的一个重要发展方向
 * 在今后的虚拟机中，逃逸分析技术肯定会支撑起一些列实用有效的优化技术，逃逸分析技术前途无量
 *
 *
 */

public class DoEscapeAnalysis {

    public void locate(){
        byte b[]  =new byte[2];
        b[0] =1;
    }

    /**
     * 开启逃逸分析，112ms 100ms 18ms
     * gc 3
     *
     * [GC (Allocation Failure)  2048K->728K(9728K), 0.0075942 secs][GC (Allocation Failure)  2776K->1155K(9728K), 0.0020239 secs]
     [GC (Allocation Failure)  3203K->1469K(9728K), 0.0043306 secs][GC (Allocation Failure)  3517K->1590K(9728K), 0.0011566 secs]
     [GC (Allocation Failure)  3638K->1582K(9728K), 0.0014394 secs]
     14
     *
     * 关闭逃逸分析：218ms 144ms
     * gc 20+  15+
     *
     * 减少gc次数？
     *
     * */
    @Test
    public void test(){
        long time = System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            locate();
        }
        System.out.println(System.currentTimeMillis()-time);
    }
}
