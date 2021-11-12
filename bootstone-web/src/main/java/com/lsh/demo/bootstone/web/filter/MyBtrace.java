package com.lsh.demo.bootstone.web.filter;

import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.*;

import java.lang.management.MemoryUsage;

import static org.openjdk.btrace.core.BTraceUtils.Strings.str;
import static org.openjdk.btrace.core.BTraceUtils.Strings.strcat;
import static org.openjdk.btrace.core.BTraceUtils.println;

/**
 * 不能创建对象
 * 不能抛出或者捕获异常
 * 不能用synchronized关键字
 * 不能对目标程序中的instace或者static变量
 * 不能调用目标程序的instance或者static方法
 * 脚本的field、method都必须是static的
 * 脚本不能包括outer,inner,nested class
 * 脚本中不能有循环,不能继承任何类,任何接口与assert语句
 *
 * 使用场景：
 * 比如哪些方法执行太慢，例如监控执行时间超过1s的方法
 *
 * 查看哪些方法调用了 System.gc() ，调用栈是怎样的
 *
 * 查看方法参数或对象属性
 *
 * 哪些方法发生了异常
 */
@BTrace
public class MyBtrace {

    /**
     * @ProbeClassName：用来标记脚本方法中的参数，能够获取当前拦截方法的类名；
     *
     * @ProbeMethodName：用来标记脚本方法中的参数，能够获取当前拦截的方法名；
     *
     * @Self：用来标记跟踪脚本方法中的参数，能够获取拦截方法运行时的实例，获取实例后通过反射机制可以获取对象的各类信息；
     *
     * @Return：用来标记跟踪脚本方法中的参数，能够获取当前拦截的方法的返回值；
     *
     * @Duration：用来标记跟踪脚本方法中的参数，能够获取当前拦截的方法的执行时间；
     *
     */
    @OnMethod(clazz = "com.lsh.demo.bootstone.web.filter.GetAllAnnos.addAllConfigs",method = "addAllConfigs",location=@Location(Kind.RETURN))
    public void testTimeA(@ProbeClassName String pcm, @ProbeMethodName String pmn, @Self Object self, @Duration long duration, @Return  Object result){
        println(strcat(strcat(strcat(strcat(pcm,"#"),pmn)," called in"),str(self)));
        println(strcat(strcat("result is ",str(result)),strcat(" duration is ",str(duration))));
    }

    //每过4秒钟打印应用程序相关内存信息
    @OnTimer(4000)
    public static void printMem() {
        println("Heap:");
        println(BTraceUtils.Sys.Memory.heapUsage());
        println("Non-Heap:");
        println(BTraceUtils.Sys.Memory.nonHeapUsage());
    }


    //可以在内存超过指定阈值的时候进行相关操作
    @OnLowMemory(pool = "Tenured Gen",threshold=6000000)
    public static void onLowMem(MemoryUsage mu) {
        println(mu);
    }
}
