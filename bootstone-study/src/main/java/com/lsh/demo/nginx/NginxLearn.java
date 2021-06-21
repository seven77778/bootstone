package com.lsh.demo.nginx;

/**
 * 优点：1.负载均衡，默认配置下会去轮询每一台服务器
 * 2.热部署
 * 3.可以解决跨越
 * 4.Nginx本身是支持高并发的，并且内存消耗小
 * 5.CPU亲和，把他的每个work线程固定在一个CPU上，减少了上下文切换带来的开支
 *
 * 缺点：1.由于是单进程多线程，如果进程挂掉，影响较大
 */
public class NginxLearn {
}
