package com.kate.demo.infrastructure.nio;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @className：NIOClient
 * @description：TODO
 * @createTime：2022/5/6 16:12
 * @author：kate_chen
 */
@Slf4j
public class NIOClient {
    public static void main(String[] args) throws IOException {
        //打开数据交互的channel
        val socketChannel = SocketChannel.open();
        //设置通道非阻塞
        socketChannel.configureBlocking(false);
        //因为客户端连接服务器，设置服务器的ip地址和端口
        val inetSocketAddress = new InetSocketAddress("10.30.26.94", 6666);
        //连接服务器，如果未连接成功就提示等待
        if (!socketChannel.connect(inetSocketAddress)) {
            while(!socketChannel.finishConnect()){
                log.info("因为连接需要时间，客户端不会阻塞，请等待！！");
            }
            log.info("连接中，请等待");
        }
        //连接服务器，成功则发送数据
        //准备数据
        String str = "包11111111111111111";
        val wrap = ByteBuffer.wrap(str.getBytes());

        socketChannel.write(wrap);

        System.in.read();


    }
}
