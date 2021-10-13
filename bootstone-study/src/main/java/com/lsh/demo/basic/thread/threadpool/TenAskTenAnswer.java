package com.lsh.demo.basic.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 2  线程池创建之后，会立即创建核心线程么
 * -- 不会。
 *从上面的源码可以看出，在刚刚创建ThreadPoolExecutor的时候，线程并不会立即启动，而是要等到有任务提交时才会启动，除非调用了 prestartCoreThread/prestartAllCoreThreads事先启动核心线程。
 *
 * 3 核心线程永远不会销毁么？
 * -- 在JDK1.6之后，如果allowsCoreThreadTimeOut=true，核心线程也可以被终止。
 * 这个问题有点tricky。首先我们要明确一下概念，虽然在JavaDoc中也使用了“core/non-core threads”这样的描述，但其实这是一个动态的概念，JDK并没有给一部分线程打上“core”的标记，做什么特殊化的处理。
 * 这个问题我认为想要探讨的是闲置线程终结策略的问题。
 *
 * 在JDK1.6之前，线程池会尽量保持corePoolSize个核心线程，即使这些线程闲置了很长时间。这一点曾被开发者诟病，所以从JDK1.6开始，提供了方法allowsCoreThreadTimeOut，如果传参为true，则允许闲置的核心线程被终止。
 *
 * 请注意这种策略和corePoolSize=0的区别。我总结的区别是：
 *
 * corePoolSize=0：在一般情况下只使用一个线程消费任务，只有当并发请求特别多、等待队列都满了之后，才开始用多线程。
 *
 *
 * allowsCoreThreadTimeOut=true && corePoolSize>1：在一般情况下就开始使用多线程（corePoolSize个），当并发请求特别多，等待队列都满了之后，继续加大线程数。但是当请求没有的时候，允许核心线程也终止。
 *
 *
 * 所以corePoolSize=0的效果，基本等同于allowsCoreThreadTimeOut=true && corePoolSize=1，但实现细节其实不同。
 *
 * 4 如何保证线程不被销毁？
 * -- 设置优先级，no，优先级只是影响运行的级别
 *
 * -- 实现方式非常巧妙，核心线程（Worker）即使一直空闲也不终止，是通过workQueue.take()实现的，它会一直阻塞到从等待队列中取到新的任务。非核心线程空闲指定时间后终止是通过workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS)实现的，一个空闲的Worker只等待keepAliveTime，如果还没有取到任务则循环终止，线程也就运行结束了。
 *  Worker本身就是个线程，它再调用我们传入的Runnable.run()，会启动一个子线程么？如果你还没有答案，再回想一下Runnable和Thread的关系。
 *
 * 5 空闲线程过多会有什么问题?
 *  -- 线程池保持空闲的核心线程是它的默认配置，一般来讲是没有问题的，因为它占用的内存一般不大。怕的就是业务代码中使用ThreadLocal缓存的数据过大又不清理。
 *
 *
 * 如果你的应用线程数处于高位，那么需要观察一下YoungGC的情况，估算一下Eden大小是否足够。如果不够的话，可能要谨慎地创建新线程，并且让空闲的线程终止；必要的时候，可能需要对JVM进行调参。
 *
 * 6 keepAliveTime=0会怎么样?
 * --在JDK1.8中，keepAliveTime=0表示非核心线程执行完立刻终止。
 *
 * 默认情况下，keepAliveTime小于0，初始化的时候才会报错；但如果allowsCoreThreadTimeOut，keepAliveTime必须大于0，不然初始化报错。
 *
 * 7  怎么进行异常处理?
 * 不论是用execute还是submit，都可以自己在业务代码上加try-catch进行异常处理。我一般喜欢使用这种方式，因为我喜欢对不同业务场景的异常进行差异化处理，至少打不一样的日志吧。
 *
 *
 * 如果是execute，还可以自定义线程池，继承ThreadPoolExecutor并复写其afterExecute(Runnable r, Throwable t)方法。
 *
 *
 * 或者实现Thread.UncaughtExceptionHandler接口，实现void uncaughtException(Thread t, Throwable e);方法，并将该handler传递给线程池的ThreadFactory。
 *
 *
 * 但是注意，afterExecute和UncaughtExceptionHandler都不适用submit。因为通过上面的FutureTask.run()不难发现，它自己对Throwable进行了try-catch，封装到了outcome属性，
 * 所以底层方法execute的Worker是拿不到异常信息的。
 *
 * 8 线程池需不需要关闭?
 * -- 一般来讲，线程池的生命周期跟随服务的生命周期。如果一个服务（Service）停止服务了，那么需要调用shutdown方法进行关闭。
 * 所以ExecutorService.shutdown在Java以及一些中间件的源码中，是封装在Service的shutdown方法内的。
 *
 * 如果是Server端不重启就不停止提供服务，我认为是不需要特殊处理的。
 *
 * 9 shutdown和shutdownNow的区别?
 *  -- shutdown => 平缓关闭，等待所有已添加到线程池中的任务执行完再关闭。
 *
 * shutdownNow => 立刻关闭，停止正在执行的任务，并返回队列中未执行的任务。
 *
 * 10  Spring中有哪些和ThreadPoolExecutor类似的工具?
 *  -- SimpleAsyncTaskExecutor	每次请求新开线程，没有最大线程数设置.不是真的线程池，这个类不重用线程，每次调用都会创建一个新的线程。
 * SyncTaskExecutor	不是异步的线程。同步可以用SyncTaskExecutor，但这个可以说不算一个线程池，因为还在原线程执行。这个类没有实现异步调用，只是一个同步操作。
 * ConcurrentTaskExecutor	Executor的适配类，不推荐使用。如果ThreadPoolTaskExecutor不满足要求时，才用考虑使用这个类。
 * SimpleThreadPoolTaskExecutor	监听Spring’s lifecycle callbacks，并且可以和Quartz的Component兼容.是Quartz的SimpleThreadPool的类。线程池同时被quartz和非quartz使用，才需要使用此类。
 *
 *
 * 这里我想着重强调的就是SimpleAsyncTaskExecutor，Spring中使用的@Async注解，底层就是基于SimpleAsyncTaskExecutor去执行任务，只不过它不是线程池，而是每次都新开一个线程。
 *
 * 另外想要强调的是Executor接口。Java初学者容易想当然的以为Executor结尾的类就是一个线程池，而上面的都是反例.我们可以在 JDK 的execute方法上看到这个注释：
 *
 *
 * * Executes the given command at some time in the future.  The command
 * * may execute in a new thread, in a pooled thread, or in the calling
 * * thread, at the discretion of the {@code Executor} implementation.
 *
 *
  所以，它的职责并不是提供一个线程池的接口，而是提供一个“将来执行命令”的接口。真正能代表线程池意义的，是ThreadPoolExecutor类，而不是Executor接口。
 */
public class TenAskTenAnswer {


    private static final ThreadPoolExecutor pool;
        /*
        threadFactory：给出带业务语义的线程命名。


        corePoolSize：快速启动4个线程处理该业务，是足够的。


        maximumPoolSize：IO密集型业务，我的服务器是4C8G的，所以4*2=8。


        keepAliveTime：服务器资源紧张，让空闲的线程快速释放。


        pool.allowCoreThreadTimeOut(true)：也是为了在可以的时候，让线程释放，释放资源。


        workQueue：一个任务的执行时长在100~300ms，业务高峰期8个线程，按照10s超时（已经很高了）。10s钟，8个线程，可以处理10 * 1000ms / 200ms * 8 = 400个任务左右，往上再取一点，512已经很多了。


        handler：极端情况下，一些任务只能丢弃，保护服务端。
         */
    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("po-detail-pool-%d").build();
        pool = new ThreadPoolExecutor(4, 8, 60L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(512),
                threadFactory, new ThreadPoolExecutor.AbortPolicy());
        pool.allowCoreThreadTimeOut(true);
    }

    public static void main(String[] args) {
        try {
            pool.submit(() -> {}).get();//Future
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        pool.execute(() -> {});//void
        Executors.newFixedThreadPool(10);
    }

    /**
     * 【强制】使用ThreadPoolExecutor的构造函数声明线程池，避免使用Executors类的 newFixedThreadPool和newCachedThreadPool。
     *
     *
     * 【强制】 创建线程或线程池时请指定有意义的线程名称，方便出错时回溯。即threadFactory参数要构造好。
     *
     *
     * 【建议】建议不同类别的业务用不同的线程池。
     *
     *
     * 【建议】CPU密集型任务(N+1)：这种任务消耗的主要是CPU资源，可以将线程数设置为N（CPU核心数）+1，比CPU核心数多出来的一个线程是为了防止线程偶发的缺页中断，或者其它原因导致的任务暂停而带来的影响。一旦任务暂停，CPU就会处于空闲状态，而在这种情况下多出来的一个线程就可以充分利用CPU的空闲时间。
     *
     *
     * 【建议】I/O密集型任务(2N)：这种任务应用起来，系统会用大部分的时间来处理I/O交互，而线程在处理I/O的时间段内不会占用CPU来处理，这时就可以将CPU交出给其它线程使用。因此在I/O密集型任务的应用中，我们可以多配置一些线程，具体的计算方法是2N。
     *
     *
     * 【建议】workQueue不要使用无界队列，尽量使用有界队列。避免大量任务等待，造成OOM。
     *
     *
     * 【建议】如果是资源紧张的应用，使用allowsCoreThreadTimeOut可以提高资源利用率。
     *
     *
     * 【建议】虽然使用线程池有多种异常处理的方式，但在任务代码中，使用try-catch最通用，也能给不同任务的异常处理做精细化。
     *
     *
     * 【建议】对于资源紧张的应用，如果担心线程池资源使用不当，可以利用ThreadPoolExecutor的API实现简单的监控，然后进行分析和优化。
     */

}
