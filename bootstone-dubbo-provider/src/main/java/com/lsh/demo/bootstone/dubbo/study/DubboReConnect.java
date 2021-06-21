package com.lsh.demo.bootstone.dubbo.study;

import org.apache.zookeeper.ClientCnxn;

/**
 * dubbo 的重连机制
 *
 * @see ClientCnxn.SendThread#run()
 * 在zk关闭后，该线程一直在跑
 * 关闭zk 的瞬间，WARN  org.apache.zookeeper.ClientCnxn - Session 0x1000e1478cb0000 for server 127.0.0.1/127.0.0.1:2181, unexpected error, closing socket connection and attempting reconnect
 * java.io.IOException: 远程主机强迫关闭了一个现有的连接。
 *
 * 说明是有检测的，那么问题来了，哪里是监控的起点呢，在哪个方法哪个类 todo
 */
public class DubboReConnect {
}
