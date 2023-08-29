package org.example.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example.netty
 *
 * @author: YW
 * @description:
 * @data: 2023/8/25 10:43
 */
public class AppClient {
    public void run() {
        //定义线程池
        NioEventLoopGroup group=new NioEventLoopGroup();
        //启动客户端辅助程序
        Bootstrap bootstrap =new Bootstrap();
        try {
        bootstrap.group(group)
                //选择初始化一个什么样的channel
                .channel(NioSocketChannel.class)
                //端口号8081
                .remoteAddress(new InetSocketAddress(8080))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new MyChannelHandler2());
                    }
                });


        ChannelFuture channelFuture=null;

            //连接
            channelFuture = bootstrap.connect().sync();
            //获取channel
            channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("hello".getBytes(StandardCharsets.UTF_8)));
            //阻塞
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new AppClient().run();
    }
}
