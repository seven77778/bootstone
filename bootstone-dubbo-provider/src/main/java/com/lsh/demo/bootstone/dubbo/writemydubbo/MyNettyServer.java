package com.lsh.demo.bootstone.dubbo.writemydubbo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.dubbo.remoting.transport.dispatcher.all.AllDispatcher;
import org.apache.dubbo.remoting.transport.dispatcher.direct.DirectDispatcher;
import org.springframework.stereotype.Component;

@Component
public class MyNettyServer {

    public static void main(String[] args) throws Exception {
        //BossGroup线程组只有一个线程处理客户端连接请求，连接完成后将完成三次握手的SocketChannel连接分发给WorkerGroup处理读写请求，
        // 这两个线程组被称为「IO线程」。
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        /**
         * 我们再引出「业务线程」这个概念。服务生产者接收到请求后，
         * 如果处理逻辑可以快速处理完成，那么可以直接放在IO线程处理，从而减少线程池调度与上下文切换。
         * 但是如果处理逻辑非常耗时，或者会发起新IO请求例如查询数据库，那么必须派发到业务线程池处理。
         *
         * DUBBO提供了多种线程模型，选择线程模型需要在配置文件指定dispatcher属性：
         *
         *
         * 线程模型在项目初始化的时候确定，看配置
         * 生产者和消费者默认线程模型都会使用AllDispatcher，ChannelHandlers.wrap方法可以获取Dispatch自适应扩展点。
         * 如果我们在配置文件中指定dispatcher，扩展点加载器会从URL获取属性值加载对应线程模型。
         *
         * @see DirectDispatcher 全部在IO线程中执行
         * 都是实现了netty 的 ChannelHandler 接口
         *
         * @see AllDispatcher 全部在业务线程
         *
         */
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MyNettyServerHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(7777).sync();
            System.out.println("服务端准备就绪");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
