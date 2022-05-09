package com.kate.demo.infrastructure.Server;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @className：groupChatServer
 * @description：群聊系统服务端
 * @createTime：2022/5/7 10:10
 * @author：kate_chen
 */
@Slf4j
public class groupChatServer {
    //定义相关的属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    @Getter
    int port = 6667;

    //初始化工作
    public groupChatServer() {
        try {
            //获取选择器
            selector = Selector.open();
            //获取监听通道
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.bind(new InetSocketAddress(port));
            //设置非阻塞模式
            listenChannel.configureBlocking(false);
            //将该listenChannel注册到selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //服务端启动并监听6667端口
    public void listen() {
        try {
            //循环处理
            while (true) {
                val i = selector.select(2000);
                //log.info("2秒阻塞持续监听中。。。。");
                if (i > 0) {
                    //当出现IO事件的时候
                    val selectionKeys = selector.selectedKeys();
                    //遍历得到selectionKey集合
                    val iterator = selectionKeys.iterator();
                    //取出selectionKey
                    if (iterator.hasNext()) {
                        val next = iterator.next();
                        //serverSocketChannel监听到accept
                        if (next.isAcceptable()) {
                            val socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            log.info("客户端监听通道连接成功，生成一个socketChannel" + socketChannel.hashCode());
                            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                            log.info(socketChannel.getRemoteAddress() + ":::上线");
                        } else if (next.isReadable()) {
                            //封装方法，处理数据交互方法
                            log.info("socketChannel开始交互数据.......");
                            this.readData(next);
                        } else if (next.isConnectable()) {
                            log.info("socketChannel.isConnectable.......");
                        } else if (next.isWritable()) {
                            log.info("socketChannel.isWritable.......");
                        }
                        //删除key，移除迭代器
                        iterator.remove();
                    }
                } else {
                   // log.info("监听器监听IO事件中，请等待。。。。。。");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }


    }

    //封装方法：读取SelectionKey进行数据读取
    private void readData(SelectionKey sk) throws IOException {
        //定义一个socketChannel
        SocketChannel sc = null;
        try {
            //得到channel
            sc = (SocketChannel) sk.channel();
            //得到buffer
            ByteBuffer byteBuffer = (ByteBuffer) sk.attachment();
            //开始读取并确认读取状态
            val read = sc.read(byteBuffer);
            if (read > 0) {
                //array()方法：获取缓冲区的字节数组
                log.info("客户端成功接收到消息，开始加载消息：：" + new String(byteBuffer.array()));
            }
            //向其他的client转发消息
            //封装方法进行转发消息
            this.sendMsgToOtherClient("发送消息给其他客户端！！！", sc);


        } catch (IOException e) {
            //throw new RuntimeException(e);
            //如果读取数据出现异常读取不到数据，可能是发生了异常
            //异常包括了"用户下线"
            log.info("出现连接异常：：" + sc.getRemoteAddress() + "::离线了");
            //取消注册
            sk.cancel();
            //关闭通道
            sc.close();
        }
    }


    //封装方法：转发消息给其他客户
    //注意：转发消息的时候需要排除"发送客服端"
    private void sendMsgToOtherClient(String sendMsg, SocketChannel selfSocketChannel) throws IOException {
        log.info("服务器消息转发中");
        //遍历所有selector注册到的SocketChannel，并排除self
        //for(遍历目标：集合)
        //keys():得到所有注册在selector上的通道
        for (SelectionKey key : selector.keys()) {
            //转发是写入通道
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (socketChannel instanceof SocketChannel && socketChannel != selfSocketChannel) {
                log.info("开始转发" + socketChannel.getRemoteAddress() + "的消息");
                socketChannel.write(ByteBuffer.wrap(sendMsg.getBytes()));
            }
        }

    }

    public static void main(String[] args) {
        val groupChatServer = new groupChatServer();
        log.info("群聊系统服务器开始监听！！！");
        groupChatServer.listen();

    }
}
