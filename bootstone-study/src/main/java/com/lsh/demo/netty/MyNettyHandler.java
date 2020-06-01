package com.lsh.demo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by lsh on 2020-05-26.
 */
public class MyNettyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("this is channelRegistered");
        super.channelRegistered(ctx);
    }
}
