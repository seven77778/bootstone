package com.lsh.demo.bootstone.dubbo.writemydubbo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.stereotype.Component;

/**
 * 可以使用postman请求
 * 127.0.0.1:7778/test1
 */
public class MyHttpNettyClient {
    public static void main(String[] args) {
        int port = 7778;
        new MyHttpNettyClient().bind(port);
    }

    public void bind(int port) {
        /**
         * interface EventLoopGroup extends EventExecutorGroup extends ScheduledExecutorService extends ExecutorService
         * 配置服务端的 NIO 线程池,用于网络事件处理，实质上他们就是 Reactor 线程组
         * bossGroup 用于服务端接受客户端连接，workerGroup 用于进行 SocketChannel 网络读写
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            /**
             * ServerBootstrap 是 Netty 用于启动 NIO 服务端的辅助启动类，用于降低开发难度
             */


            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            /**服务器启动辅助类配置完成后，调用 bind 方法绑定监听端口，调用 sync 方法同步等待绑定操作完成*/
            ChannelFuture f = b.bind(port).sync();

            System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.........");

            /**下面会进行阻塞，等待服务器连接关闭之后 main 方法退出，程序结束*/
            f.channel().closeFuture().sync();

            //客户端开始心跳



        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /**优雅退出，释放线程池资源*/
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 初始化连接
     */
    @Component
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        public void initChannel(SocketChannel socketChannel) throws Exception {
            /**
             * 设置 netty 服务端的 handler
             */
            //socketChannel.pipeline().addLast(new NettyServerHandler());

            /**
             * 如果使用 netty 搭建 http 服务端,则用下面三个设置代替上面一个设置
             */
            socketChannel.pipeline().addLast(new HttpServerCodec());// http 编解码
            socketChannel.pipeline().addLast("httpAggregator", new HttpObjectAggregator(512 * 1024)); // http 消息聚合器
            socketChannel.pipeline().addLast(new MyHttpNettyServerHanlder());
        }
    }
}
