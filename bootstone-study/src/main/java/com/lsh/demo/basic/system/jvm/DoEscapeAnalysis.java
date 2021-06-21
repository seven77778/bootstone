package com.lsh.demo.basic.system.jvm;

import org.junit.Test;

/**
 * 很重要的一个前提，在 JIT编译器中才生效
 * 【补充知识】并非所有的对象都会在堆上面分配，而没有在堆上分配的对象是因为经过逃逸分析，分析之后发现该对象的大小可以在栈上分配，不会造成栈溢出，这时，对象就可以在栈上分配。
 * 当然，如果经过逃逸分析，发现该对象在栈上分配会照成栈溢出，那么该对象就会在堆空间分配。
 *
 * 1.开启逃逸分析，分析GC有何区别
 * -XX:+DoEscapeAnalysis
 *
 * 测试参数：-server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC
 *
 * 2.逃逸分析
 * 开启 stringbuffer 耗时减少 -- 参见《程序性能优化》 p213
 *
 * 逃逸分析的基本行为就是分析对象动态作用域：当一个对象在方法中被定义后，它可能被外部方法所引用，例如作为调用参数传递到其他地方中，称为方法逃逸。
 *
 * 3.场景介绍
 * 其实，在java应用里普遍存在一种场景。一般是在方法体内，声明了一个局部变量，且该变量在方法执行生命周期内未发生逃逸（在方法体内，未将引用暴露给外面）。
 *
 * 按照JVM内存分配机制，首先会在堆里创建变量类的实例，然后将返回的对象指针压入调用栈，继续执行。
 *
 * 这是【优化前】，JVM的处理方式。
 *
 * 逃逸分析优化 - 栈上分配
 *
 * 优化原理：分析找到未逃逸的变量，将变量类的实例化内存直接在栈里分配(无需进入堆)，分配完成后，继续在调用栈内执行，最后线程结束，栈空间被回收，局部变量对象也被回收。
 *
 * 这是【优化后】的处理方式，对比可以看出，主要区别在栈空间直接作为临时对象的存储介质。从而减少了临时对象在堆内的分配数量。
 * ————————————————


 * 4.锁消除：如果能确定
 *
 *
 *
 * 5.几种 逃逸分析
3种常见的指针逃逸场景。分别是 全局变量赋值，方法返回值，实例引用传递。

 *
 *6.最终章：为什么这个技术还不成熟，来看下最坏的情况，进行了一波逃逸分析，完了发现没有一个不逃逸的，
 * 就不能做任何优化，白白浪费了 分析 的时间。


 * public class G {
 * 	public static B b;
 *
 * 	public void globalVariablePointerEscape(){//给全局变量赋值，发生逃逸
 * 		b=new B(); //全局变量+final
 *        }
 *
 * 	public B methodPointerEscape(){//方法返回值，发生逃逸
 * 		return new B(); // 这个感觉经常用啊 todo
 *    }
 *
 * 	public void instancePassPointerEscape(){
 * 		methodPointerEscape().printClassName(this);//实例引用发生逃逸
 *    }
 *
 *
 * }
 *
 * class B{
 * 	public void printClassName(G g){
 * 		System.out.println(g.getClass().getName());
 *    }
 * }
 *
 *
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
