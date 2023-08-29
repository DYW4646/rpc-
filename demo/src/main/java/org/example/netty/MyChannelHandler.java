package org.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example.netty
 *
 * @author: YW
 * @description:
 * @data: 2023/8/25 19:27
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //收到消息并处理
        ByteBuf byteBuf =(ByteBuf) msg;
        System.out.println("收到客户端发来的消息"+byteBuf.toString(StandardCharsets.UTF_8));
        //写入并发送信息
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("我是服务端，已收到",StandardCharsets.UTF_8));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
