package com.ken.work.io;

import java.io.*;
import java.net.Socket;

/**
 * Created by s on 2018/3/7.
 */
public class Client {


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 7900);
        try {

            byte[] buffer = new byte[50];

            System.out.println("write");
            OutputStream out = socket.getOutputStream();
            out.write("hello".getBytes("UTF-8"));
            out.flush();

//
//            InputStream in = socket.getInputStream();
//            int count;
//            while ((count = in.read(buffer)) != -1) {
//                String s = new String(buffer, 0, count);
//                System.out.println(s);
//            }
//

            InputStream in=socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            //3.利用流按照一定的操作，对socket进行读写操作
            String replyInfo=null;
            while(!((replyInfo=br.readLine())==null)){
                System.out.println("接收服务器的数据信息："+replyInfo);
            }


            out.close();
            in.close();
            socket.close();
        } finally {
//            socket.close();
        }

    }
}
