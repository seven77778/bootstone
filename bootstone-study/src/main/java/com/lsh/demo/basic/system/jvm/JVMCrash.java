package com.lsh.demo.basic.system.jvm;

/**
 * Created by lsh on 2019-04-29.
 *
 * see https://www.cnblogs.com/jjzd/p/6519686.html
 *
 * 第一：这里一个重要信息是“SIGSEGV(0xb)”表示jvm crash时正在执行jni代码，而不是在执行java或者jvm的代码，
 * 如果没有在应用程序里手动调用jni代码，那么很可能是JIT动态编译时导致的该错误。
 *
 * 其中SIGSEGV是信号名称，0xb是信号码，pc=0x00007fb8b18fdc6c指的是程序计数器的值，
 * pid=191899是进程号，tid=140417770411776是线程号。
 *
 * 进程 ：progress
 * 线程 ：threadlocal
 *
 * 第二：除了“SIGSEGV(0xb)”以外，常见的描述还有“EXCEPTION_ACCESS_VIOLATION”，
 * 该描述表示jvm crash时正在执行jvm自身的代码，这往往是因为jvm的bug导致的crash；
 *
 *
 * 第三：另一种常见的描述是“EXCEPTION_STACK_OVERFLOW”，该描述表示这是个栈溢出导致的错误，
 * 这往往是应用程序中存在深层递归导致的。
 *
 *
 */
public class JVMCrash {


    /*举个栗子
        #
        # A fatal error has been detected by the Java Runtime Environment:
        #
        #  EXCEPTION_ACCESS_VIOLATION (0xc0000005) at pc=0x7735e39e, pid=5096, tid=0x000005d8
        #
        # JRE version: Java(TM) SE Runtime Environment (8.0_201-b09) (build 1.8.0_201-b09)
        # Java VM: Java HotSpot(TM) Client VM (25.201-b09 mixed mode windows-x86 )
        # Problematic frame:
        # C  [ntdll.dll+0x2e39e]
        #
        # Failed to write core dump. Minidumps are not enabled by default on client versions of Windows
        #
        # If you would like to submit a bug report, please visit:
        #   http://bugreport.java.com/bugreport/crash.jsp
        # The crash happened outside the Java Virtual Machine in native code.
        # See problematic frame for where to report the bug.
        #
        Current threadlocal (0x14ea6800):  JavaThread "Finalizer" daemon [_thread_in_native, id=1496, stack(0x15120000,0x15170000)]


     */

    /*

        C: Native C frame
        j: Interpreted Java frame
        V: VMframe
        v: VMgenerated stub frame
        J: Other frame types, including compiled Java frames
     */

}
