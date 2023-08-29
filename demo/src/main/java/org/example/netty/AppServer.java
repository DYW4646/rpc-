package org.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example.netty
 *
 * @author: YW
 * @description:
 * @data: 2023/8/25 11:17
 */
public class AppServer {
    private int port;

    public AppServer(int port) {
        this.port = port;
    }

    public void start(){
        EventLoopGroup boss= new NioEventLoopGroup(2);
        EventLoopGroup worker= new NioEventLoopGroup(10);
        //
        try{
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            //配置服务器
            serverBootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new MyChannelHandler());
                        }});
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            try{
                boss.shutdownGracefully().sync();
                worker.shutdownGracefully().sync();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }


    public static void main(String[] args) {
        new AppServer(8080).start();
    }
}
