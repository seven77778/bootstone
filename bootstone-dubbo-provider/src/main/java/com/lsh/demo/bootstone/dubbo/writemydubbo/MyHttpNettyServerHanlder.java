package com.lsh.demo.bootstone.dubbo.writemydubbo;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

/**
 * 2021年4月28日 add heart beat -- http好像不太合适，不过应该也可以，从
 * httpNettyClient 发出心跳
 */
@Component
public class MyHttpNettyServerHanlder extends SimpleChannelInboundHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest) {
            FullHttpRequest req = (FullHttpRequest) msg;

            try {
                // 1.获取URI
                String uri = req.uri();
                System.out.println("uri:" + uri);
                // 2.获取请求体
                ByteBuf buf = req.content();
                String content = buf.toString(CharsetUtil.UTF_8);

                // 3.根据请求的方法uri不同处理不同的逻辑
                Object rc = new Object();
                switch (uri) {
                    case "/test1":
                        System.out.println("jahahhhha");
                        // ......
                        break;
                    case "/ltest2":
                        // ......
                        break;
                    default:
                        break;
                }
                // 4.返回结果
                response(ctx, rc);
            } finally {
                req.release();
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到请求---" + msg);
    }


    private void response(ChannelHandlerContext ctx, Object c) {

        // 1.设置响应
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(JSONObject.toJSONString(c), CharsetUtil.UTF_8));
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        // 2.发送
        // 注意必须在使用完之后，close channel
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }
}
