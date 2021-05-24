package com.lsh.demo.bootstone.dubbo.study;

import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.dubbo.config.DubboShutdownHook;
import org.apache.dubbo.rpc.support.RpcUtils;

/**
 * 1. 容器发布完成，dubbo中接口还有两个提供者，一个是老的，一个是新的，不过老的是很老 很老了 fixme --从服务治理开始
 *
 * 2.dubbo invoke 入口 -- DubboNamespaceHandler
 *
 * @see DubboShutdownHook -- 清理工作
 *
 */

public class DubboCodeLearn {

    /**
     * @see RpcUtils#isOneway(org.apache.dubbo.common.URL, org.apache.dubbo.rpc.Invocation)
     *
     * 1.在client端，判断isoneway，是否含有 return
     *
     *
     *
     * 2.关键字 State change: RECONNECTED -- 是怎么做的监听，while循环之类？
     * @see CuratorFrameworkImpl#validateConnection(org.apache.zookeeper.Watcher.Event.KeeperState)
     */
}
