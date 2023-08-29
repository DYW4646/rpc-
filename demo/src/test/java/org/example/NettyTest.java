package org.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example
 *
 * @author: YW
 * @description:
 * @data: 2023/8/27
 *
 * 1，gzip的readAllBytes方法是jdk9才有的，老师推荐jdk17
 * 2，
 */


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

@Test
//压缩
    public void testCompress() throws IOException {
        byte[] buf=new byte[]{12,12,12,12,23,35,56,56,56,56,78,45};
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        //gzip操作byte的流
        GZIPOutputStream gzipOutputStream=new GZIPOutputStream(baos);
        //gzip将buf写入byte的stream
        gzipOutputStream.write(buf);
        gzipOutputStream.finish();

        byte[] bytes= baos.toByteArray();
        System.out.println(Arrays.toString(bytes));
    }

    @Test
    //解压缩
    public void testDecompress() throws IOException {
        //注意这是压缩后的，不是原数据
        byte[] buf=new byte[]{31, -117, 8, 0, 0, 0, 0, 0, 0, -1, -29, -31, -31, -31, 17, 87, -74, 0, 2, 63, 93, 0, -97, 61, 92, 77, 12, 0, 0, 0};

        ByteArrayInputStream bais=new ByteArrayInputStream(buf);
        GZIPInputStream gzipintputStream=new GZIPInputStream(bais);


        byte[] bytes= gzipintputStream.readAllBytes();
        System.out.println(Arrays.toString(bytes));
    }
}
