package com.lsh.demo.basic.system.jvm;

/**
 * 重要前提：在JTI编译器 的环境下，解释器无效
 *
 *
 *
 */
public class EscapeTest {

    public static Object globalVariableObject;

    public Object instanceObject;

    public void globalVariableEscape(){
        globalVariableObject = new Object(); //静态变量,外部线程可见,发生逃逸
    }

    public void instanceObjectEscape(){
        instanceObject = new Object(); //赋值给堆中实例字段,外部线程可见,发生逃逸
    }

    public Object returnObjectEscape(){
        return new Object();  //返回实例,外部线程可见，发生逃逸
    }

    public void noEscape(){
        synchronized (new Object()){
            //仅创建线程可见,对象无逃逸
        }
        Object noEscape = new Object();  //仅创建线程可见,对象无逃逸
    }

    /**
     * 当判断出对象不发生逃逸时，编译器可以使用逃逸分析的结果作一些代码优化
     *
     * 将堆分配转化为栈分配。如果某个对象在子程序中被分配，并且指向该对象的指针永远不会逃逸，该对象就可以在分配在栈上，而不是在堆上。在有垃圾收集的语言中，这种优化可以降低垃圾收集器运行的频率。
     * 同步消除（锁消除）。如果发现某个对象只能从一个线程可访问，那么在这个对象上的操作可以不需要同步。
     * 分离对象或标量替换。如果某个对象的访问方式不要求该对象是一个连续的内存结构，那么对象的部分（或全部）可以不存储在内存，而是存储在CPU寄存器中
     *
     *
     */
}
