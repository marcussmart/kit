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
//            handleRead(currentKey);
            handleRead(socketChannel);
//            socketChannel.register(selector, SelectionKey.OP_WRITE);

        } else if (currentKey.isWritable()) {
            System.out.println("writable");
//            SocketChannel socketChannel = (SocketChannel) currentKey.channel();
//            handleWrite(socketChannel);
        }

    }


    private void handleWrite(SocketChannel socketChannel) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.allocate(20);
        writeBuffer.put("handleWrite".getBytes("UTF-8"));
        socketChannel.write(writeBuffer);
    }


    private void handleRead(SocketChannel socketChannel)  {

        ByteBuffer readBuffer = ByteBuffer.allocate(2);

        int count;
        try {
            System.out.println("--read: ");
            while (true) {

                count = socketChannel.read(readBuffer);
                if (count > 0) {

                    String s = new String(readBuffer.array(), 0 ,count, "UTF-8");
                    System.out.println(s);

                    readBuffer.flip();
                } else {
                    System.out.println("count = " + count);
                    break;
                }
            }

            System.out.println("--write: ");
             ByteBuffer writeBuffer = ByteBuffer.allocate(2);
            System.out.println(writeBuffer.remaining());
            byte[] data = "server".getBytes("UTF-8");

            for(int i = 0; i < data.length;) {

                int limit = writeBuffer.limit();
                limit = (data.length - i) > limit ? limit : data.length - i;

                writeBuffer.clear();
                System.out.println("i=" + i + " limit=" + limit + " remain=" + writeBuffer.remaining());
                writeBuffer.put(data, i, limit);
                System.out.println("i=" + i + " limit=" + limit + " remain=" + writeBuffer.remaining());
                writeBuffer.flip();
                socketChannel.write(writeBuffer);

                i = i + limit;
            }

            System.out.println("exist");


            socketChannel.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public void handleRead(SelectionKey key) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        SocketChannel socketChannel = (SocketChannel)key.channel();
        while(true) {
            int readBytes = socketChannel.read(byteBuffer);
            if(readBytes>0) {
                String s = new String(byteBuffer.array(), 0, readBytes);
                System.out.println("Server: data = " + s);
//                log.info("Server: readBytes = " + readBytes);
//                log.info("Server: data = " + new String(byteBuffer.array(), 0, readBytes));
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                break;
            }
        }
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        NotBlockedIO notBlockedIO = new NotBlockedIO();
        notBlockedIO.init();
        notBlockedIO.listen();
    }
}
