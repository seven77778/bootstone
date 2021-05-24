package com.lsh.demo.bootstone.dubbo.writemydubbo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyNettyClient {

    /**
     * 使用 3 个线程模拟三个客户端
     */
    public static void main(String[] args) throws Exception {

        while (true){
            new Thread(new MyThread()).start();
            Thread.sleep(300);
        }

    }

    static class MyThread implements Runnable {
        /**
         * 服务端 ip 及端口
         */
        @Override
        public void run() {
            connect("127.0.0.1", 7777);
        }

        public void connect(String host, int port) {
            /**配置客户端 NIO 线程组/池*/
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                /**
                 * Bootstrap 与 ServerBootstrap 都继承(extends)于 AbstractBootstrap
                 * 创建客户端辅助启动类,并对其配置,与服务器稍微不同，这里的 Channel 设置为 NioSocketChannel
                 * 然后为其添加 Handler，这里直接使用匿名内部类，实现 initChannel 方法
                 * 作用是当创建 NioSocketChannel 成功后，在进行初始化时,将它的ChannelHandler设置到ChannelPipeline中，用于处理网络I/O事件
                 */
                Bootstrap b = new Bootstrap();
                b.group(group).channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new MyNettyClientHandler());
                            }
                        });

                /**connect：发起异步连接操作，调用同步方法 sync 等待连接成功*/
                ChannelFuture channelFuture = b.connect(host, port).sync();
                System.out.println(Thread.currentThread().getName() + ",客户端发起异步连接..........");

                /**等待客户端链路关闭*/
                channelFuture.channel().closeFuture().sync();

                // add heart beat
                try {
                    Channel channel = b.connect("127.0.0.1", 7777).sync().channel();
                    String text = "Heartbeat Packet";
                    // 随机休息几秒，模拟网络不稳定，间断给服务端发送心跳的情况。
                    while (channel.isActive()) {
                        Thread.sleep(1000);
                        channel.writeAndFlush(text);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    group.shutdownGracefully();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                /**优雅退出，释放NIO线程组*/
                group.shutdownGracefully();
            }
        }

    }

}
