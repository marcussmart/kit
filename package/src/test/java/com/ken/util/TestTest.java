package com.ken.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by s on 2016/9/13.
 */
public class TestTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        String s = "%E8%8E%B7%E5%BE%97%E9%AB%98%E4%BD%8E%E6%9D%A0";
        System.out.println(URLDecoder.decode(s, "UTF-8"));

        String s1 = "%BF%CD%BB%A7%B6%CB%CA%B9%D3%C3";
        System.out.println(URLDecoder.decode(s1, "GBK"));

        String t = "�ͻ���";
        System.out.println(URLEncoder.encode(s, "UTF-8"));

        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println((int) (date.getTime() / 1000));

        String a =
        "<td class=\"text-center\">\n" +
        "   <input type=\"text\" placeholder=\"请输入当月佣金\" />\n" +
        "</td>\n" +
        "</tr>";


        String url  = MessageFormat.format("redirect: /agent/{0}/goods", 21);
    }

}
