package com.ken.work.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by s on 2018/3/7.
 */
public class NotBlockedIO {

    private int port = 7900;
    private Selector selector;

    public void init() throws IOException {
        selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    private void listen() throws IOException {

        while (true) {

            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            SelectionKey currentKey;
            while (iterator.hasNext()) {

                currentKey = iterator.next();
                handleKey(currentKey);
                iterator.remove();
            }
        }
    }


    private void handleKey(SelectionKey currentKey) throws IOException {

        if (currentKey.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) currentKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();

            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (currentKey.isReadable()) {

            SocketChannel socketChannel = (SocketChannel) currentKey.channel();
            handleRead(socketChannel);
            socketChannel.register(selector, SelectionKey.OP_WRITE);

        } else if (currentKey.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) currentKey.channel();
            handleWrite(socketChannel);
        }

    }


    private void handleWrite(SocketChannel socketChannel) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.allocate(20);
        writeBuffer.put("handleWrite".getBytes("UTF-8"));
        socketChannel.write(writeBuffer);
    }


    private void handleRead(SocketChannel socketChannel)  {

        ByteBuffer readBuffer = ByteBuffer.allocate(2);
        ByteBuffer writeBuffer = ByteBuffer.allocate(20);

        int count;
        try {
            while ((count = socketChannel.read(readBuffer)) > 0) {
                String s = new String(readBuffer.array(), "UTF-8");
                System.out.println(s);
            }

            writeBuffer.put("server".getBytes("UTF-8"));
            socketChannel.write(writeBuffer);

        } catch (IOException ioe) {
            ioe.printStackTrace();
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        NotBlockedIO notBlockedIO = new NotBlockedIO();
        notBlockedIO.init();
        notBlockedIO.listen();
    }
}
