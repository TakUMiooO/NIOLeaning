package com.kate.demo.infrastructure.nio;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @className：NIOServer
 * @description：TODO
 * @createTime：2022/5/6 15:02
 * @author：kate_chen
 */
@Slf4j
public class NIOServer {
    public static void checkSelector(Selector s1){
        val keys = s1.keys();
        log.info("使用keys()方法：得到当前注册到selector的数量为"+keys.size());
        val selectionKeySet = s1.selectedKeys();
        log.info("使用selectedKeys方法：得到有IO响应的selector的数量为"+selectionKeySet.size());
    }
    public static void main(String[] args) throws IOException {
        //创建serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //get selector对象
        Selector selector = Selector.open();
        //绑定一个端口号，并在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //将serverSocketChannel注册到selector,连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        checkSelector(selector);
        //循环等待客户端连接
        while(true){

            if(selector.select(1000)==0){
                System.out.println("服务器等到了1秒，没有事件发生");
                continue;
            }

            //如果select()返回的不是0,获取到有事件发生的相关selectionKey集合
            //通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            val iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                //得到selectionKey
                val next = iterator.next();
                //监听到IO事件
                if (next.isAcceptable()){
                    val socketChannel = serverSocketChannel.accept();
                    log.info("客户端连接成功，生成一个socketChannel"+socketChannel.hashCode());
                    //将通道设置为非阻塞
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    //当注册完毕后，就意味着可以监听到这个OP_Read的channel
                    //程序开始的时候，一定是通过监听使用accept得到socketChannel
                    //serverSocketChannel就是监听用的channel
                    checkSelector(selector);
                }
                //监听到socketChannel数据交互使用
                if(next.isReadable()){
                    //通过迭代器获取数据交互的channel
                    SocketChannel channel = (SocketChannel) next.channel();
                    //通过迭代器获取数据交互的buffer
                    ByteBuffer byteBuffer = (ByteBuffer)next.attachment();
                    val read = channel.read(byteBuffer);
                    log.info("客户端读取：："+new String(byteBuffer.array()));
                    checkSelector(selector);
                }
                //手动将迭代器移除,防止重复操作
                iterator.remove();

            }


        }

    }
}
