package com.ken.work.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by s on 2018/3/7.
 */
public class Client {


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 7900);
        try {

            byte[] buffer = new byte[50];
            while (true) {
                System.out.println("write");
                OutputStream out = socket.getOutputStream();
                out.write("hello".getBytes("UTF-8"));


                System.out.println("read");
                InputStream in = socket.getInputStream();
                int count;
                while ((count = in.read(buffer)) > 0) {

                    String s = new String(buffer, 0, count);
                    System.out.println(s);
                }
            }
        } finally {
            socket.close();
        }

    }
}
