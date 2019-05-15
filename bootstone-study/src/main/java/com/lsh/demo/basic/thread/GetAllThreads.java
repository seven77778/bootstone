package com.lsh.demo.basic.thread;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by lsh on 2018/11/10 17:48.
 *
 * @author lsh
 * @date 2018/11/10
 */
public class GetAllThreads {

    /**
     * test how get all threads and their names
     *
     * out:
     * thread name is :Monitor Ctrl-Break
     thread name is :Attach Listener
     thread name is :Signal Dispatcher -- 分发处理发送给JVM信号的线程
     thread name is :Finalizer -- 调用对象finalize方法的线程
     thread name is :Reference Handler --清除Reference的线程?
     thread name is :main
     */
    @Test
    public void test(){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadinfos = threadMXBean.dumpAllThreads(false, false);
        for(ThreadInfo threadInfo : threadinfos){
            System.out.println("thread name is :" + threadInfo.getThreadName());
        }
    }
}
