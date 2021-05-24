package com.lsh.demo.bootstone.dubbo.study;


import org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool;
import org.apache.dubbo.remoting.transport.dispatcher.all.AllDispatcher;
import org.apache.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;

/*
现在我们知道DUBBO会选择线程池策略进行业务处理，那么应该如何估算可能产生的线程数呢？
我们首先分析一个问题：一个公司有7200名员工，每天上班打卡时间是早上8点到8点30分，
每次打卡时间系统执行时长为5秒。请问RT、QPS、并发量分别是多少？

rt=5s,并发= 7200/30乘以60 =4

qps = 4/5 = 0.8
************************
* 正确答案：
* rt=5s,qps 是每秒查询量，7200 / 1800 = 4
* 并发 = rt * qps = 20

 */

/**
 * question todo
 * 1.dubbo是怎么判断超时的，肯定有一个计时器之类的？
 *
 * 2.比如剩余5次超时，每次都进入catch，还是一次性把五次执行完
 */

/**
 * dubbo 线程池 https://mp.weixin.qq.com/s/THTUDZi43W02vWTuUMnWLQ
 * DUBBO消费者在调用选择生产者时本身就会执行预热逻辑，为什么还会出现预热不充分问题？
 * 这是因为2.5.5之前版本以及2.7.2版本预热机制是有问题的，简而言之就是获取启动时间不正确，2.7.4版本彻底解决了这个问题，所以我们要避免使用问题版本。下面我们阅读2.7.0版本预热机制源码
 *
 * @see RandomLoadBalance
 *
 * @see AllDispatcher 默认
 *
 * @see FixedThreadPool
 *
 * 1.dubbo 的业务线程池 、 IO线程池
 * IO线程池适合做一些简单的处理
 */
public class DubboThreadPool {
    /**
     *
     * <dubbo:protocol name="dubbo" dispatcher="all" />
     *
     * all
     * 所有消息都派发到业务线程池，包括请求，响应，连接事件，断开事件，心跳
     *
     * direct
     * 所有消息都不派发到业务线程池，全部在IO线程直接执行
     *
     * message
     * 只有请求响应消息派发到业务线程池，其它连接断开事件，心跳等消息直接在IO线程执行
     *
     * execution
     * 只有请求消息派发到业务线程池，响应和其它连接断开事件，心跳等消息直接在IO线程执行
     *
     * connection
     * 在IO线程上将连接断开事件放入队列，有序逐个执行，其它消息派发到业务线程池
     *
     *
     */


    /**
     *
     * 线程池配置 -- <dubbo:protocol name="dubbo" threadpool="fixed" threads="100" />
     *
     * fixed
     * 包含固定个数线程
     *
     * cached
     * 线程空闲一分钟会被回收，当新请求到来时会创建新线程
     *
     * limited
     * 线程个数随着任务增加而增加，但不会超过最大阈值。空闲线程不会被回收
     *
     * eager
     * 当所有核心线程数都处于忙碌状态时，优先创建新线程执行任务，而不是立即放入队列
     *
     */

    /**
     * 其他参数
     * dubbo.protocol.queues=100
     * -- 当线程池满了的时候，服务会立马进入失败状态，
     * 此时如果需要给provider设置等待队列的话可以尝试使用queues参数进行设置
     * 不建议使用，应该立刻抛出异常
     *
     *
     */
}
