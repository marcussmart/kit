package com.dou.qr;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by s on 2017/2/14.
 */
public class QRCodeTest {

    private QRCode qrCode = new QRCodeImpl();

    @Test
    public void testEncode1() throws Exception {
        byte[] qrCodeImg = qrCode.encode("http://translate.google.cn/#en/zh-CN/随风倒但是");

        FileOutputStream out =new FileOutputStream("D:\\QRCode1.png");
        try {
            out.write(qrCodeImg);
        } finally {
            out.close();
        }
    }

    @Test
    public void testEncode2() throws Exception {
        byte[] qrCodeImg = qrCode.encode("http://translate.google.cn/#en/zh-CN/随风倒但是", 200, 200);

        FileOutputStream out =new FileOutputStream("D:\\QRCode2.png");
        try {
            out.write(qrCodeImg);
        } finally {
            out.close();
        }
    }

    @Test
    public void testEncode3() throws Exception {
        byte[] qrCodeImg = qrCode.encode("http://translate.google.cn/#en/zh-CN/随风倒但是", "png", 200, 200);

        FileOutputStream out =new FileOutputStream("D:\\QRCode3.png");
        try {
            out.write(qrCodeImg);
        } finally {
            out.close();
        }
    }

    @Test
    public void testEncode4() throws Exception {
        byte[] qrCodeImg = qrCode.encode("http://translate.google.cn/#en/zh-CN/随风倒但是", "utf-8", "png", 200, 200);

        FileOutputStream out =new FileOutputStream("D:\\QRCode4.png");
        try {
            out.write(qrCodeImg);
        } finally {
            out.close();
        }
    }

    @Test
    public void testEncode11() throws Exception {
        byte[] qrCodeImg = qrCode.encode("http://translate.google.cn/#en/zh-CN/随风倒但是", new FileInputStream("D:\\logo.jpg"));

        FileOutputStream out =new FileOutputStream("D:\\QRCode11.png");
        try {
            out.write(qrCodeImg);
        } finally {
            out.close();
        }
    }

    @Test
    public void testEncode12() throws Exception {
        byte[] qrCodeImg = qrCode.encode("http://translate.google.cn/#en/zh-CN/随风倒但是", new FileInputStream("D:\\logo.jpg"), 250, 250);

        FileOutputStream out =new FileOutputStream("D:\\QRCode12.png");
        try {
            out.write(qrCodeImg);
        } finally {
            out.close();
        }
    }

    @Test
    public void testEncode13() throws Exception {
        byte[] qrCodeImg = qrCode.encode("http://translate.google.cn/#en/zh-CN/随风倒但是", new FileInputStream("D:\\logo.jpg"), "png", 200, 200);

        FileOutputStream out =new FileOutputStream("D:\\QRCode13.png");
        try {
            out.write(qrCodeImg);
        } finally {
            out.close();
        }
    }

    @Test
    public void testEncode14() throws Exception {
        byte[] qrCodeImg = qrCode.encode("http://translate.google.cn/#en/zh-CN/随风倒但是", new FileInputStream("D:\\logo.jpg"),  "utf-8", "png", 200, 200);

        FileOutputStream out =new FileOutputStream("D:\\QRCode14.png");
        try {
            out.write(qrCodeImg);
        } finally {
            out.close();
        }
    }



    @Test
    public void testFor() {
//        List<Object> list = null;
        List<Object> list = new ArrayList<>();
        for (Object o: list) {
            System.out.println(o);
        }
    }

}