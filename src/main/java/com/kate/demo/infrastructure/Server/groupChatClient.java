package com.kate.demo.infrastructure.Server;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.expression.spel.ast.Selection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @className：groupChatClient
 * @description：群聊系统客服端
 * @createTime：2022/5/7 14:57
 * @author：kate_chen
 */
@Slf4j
public class groupChatClient {

    //定义相关属性
    @Getter
    int PORT = 6667;
    @Getter
    String HOST = "10.30.26.94";
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName = null;

    //构造器，完成初始化工作
    public groupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel.open(new InetSocketAddress(HOST, PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到username
        userName = socketChannel.getLocalAddress().toString();
    }

    //向服务器发送消息
    public void sendMsgToServer(String Msg) throws IOException {
        try {
            log.info("日志：向服务器发送消息");
            socketChannel.write(ByteBuffer.wrap(Msg.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //读取从服务器转发过来的消息
    public void getMsgFromOtherClient() {
        try {
            if (selector.select() > 0) {
                val iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    val next = iterator.next();
                    if (next.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) next.channel();
                        val byteBuffer = ByteBuffer.allocate(1024);
                        socketChannel.read(byteBuffer);
                        log.info(socketChannel.getRemoteAddress() + "::userName::" + userName + "::content is " + new String(byteBuffer.array()));
                    }
                }
            } else {
                log.info("没有可用的通道进行数据交互");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    //最后一步：如何使用服务端
    //主要利用线程技术
    public static void main(String[] args) throws IOException {
        //
        groupChatClient groupChatClient = new groupChatClient();
        //启动一个线程
        //每隔3秒读取从服务器发送的数据
        new Thread(){
            public void run(){
                groupChatClient.getMsgFromOtherClient();
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();

        //发送数据给服务器端



    }
}
