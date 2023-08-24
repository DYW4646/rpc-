package org.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

public class NettyTest {
    @Test
    public void testByteBuffer(){
        ByteBuf header= Unpooled.buffer();
        ByteBuf body = Unpooled.buffer();
        //零拷贝:逻辑拼装（而不是物理拷贝）
        CompositeByteBuf byteBuf= Unpooled.compositeBuffer();
        byteBuf.addComponents(header,body);
    }

    @Test
    public  void testWrapper(){
        byte[] buf =new byte[1024];
        byte[] buf1 =new byte[1024];
        //共享数组内容，而不是单纯的将两个合并成一个
        ByteBuf byteBuf  =  Unpooled.wrappedBuffer(buf,buf1);
    }

    @Test
    public  void testSlice(){
        byte[] buf =new byte[1024];
        byte[] buf1 =new byte[1024];

        ByteBuf byteBuf  =  Unpooled.wrappedBuffer(buf,buf1);
        //将一个分成多个，仍然是共享
        ByteBuf buf2=byteBuf.slice(1,5);
    }
}
