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
    private String userName=null;

    //构造器，完成初始化工作
    public groupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel.open(new InetSocketAddress(HOST,PORT));
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
    public void getMsgFromOtherClient(){
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {

    }
}
