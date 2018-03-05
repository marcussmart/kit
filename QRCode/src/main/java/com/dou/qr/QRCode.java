package com.dou.qr;

import java.io.InputStream;

/**
 * Created by s on 2017/2/14.
 */
public interface QRCode {

    /**
     * 生成二维码
     * @param content 二维码内容
     * @return  二维码字节数组
     * @throws Exception
     */
    byte[] encode(String content) throws Exception;

    /**
     * 生成二维码
     * @param content 二维码内容
     * @param width 图片宽度
     * @param height 图片高度
     * @return 二维码字节数组
     * @throws Exception
     */
    byte[] encode(String content,Integer width, Integer height) throws Exception;

    /**
     * 生成二维码
     * @param content 二维码内容
     * @param format 图片格式
     * @param width 图片宽度
     * @param height 图片高度
     * @return 二维码字节数组
     * @throws Exception
     */
    byte[] encode(String content, String format, Integer width, Integer height) throws Exception;

    /**
     * 生成二维码
     * @param content 二维码内容
     * @param charset 生成的二维码内容编码格式
     * @param format 图片格式
     * @param width 图片宽度
     * @param height 图片高度
     * @return  二维码字节数组
     * @throws Exception
     */
    byte[] encode(String content, String charset, String format, Integer width, Integer height) throws Exception;



    /**
     * 生成中间带logo的二维码
     * @param content 二维码内容
     * @param logo logo
     * @return  二维码字节数组
     * @throws Exception
     */
    byte[] encode(String content, InputStream logo) throws Exception;

    /**
     * 生成中间带logo的二维码
     * @param content 二维码内容
     * @param logo logo
     * @param width 图片宽度
     * @param height 图片高度
     * @return 二维码字节数组
     * @throws Exception
     */
    byte[] encode(String content, InputStream logo, Integer width, Integer height) throws Exception;

    /**
     * 生成中间带logo的二维码
     * @param content 二维码内容
     * @param logo logo
     * @param format 图片格式
     * @param width 图片宽度
     * @param height 图片高度
     * @return 二维码字节数组
     * @throws Exception
     */
    byte[] encode(String content, InputStream logo, String format, Integer width, Integer height) throws Exception;


    /**
     * 生成中间带logo的二维码
     * @param content 二维码内容
     * @param logo logo
     * @param charset 生成的二维码内容编码格式
     * @param format 图片格式
     * @param width 图片宽度
     * @param height 图片高度
     * @return  二维码字节数组
     * @throws Exception
     */
    byte[] encode(String content, InputStream logo, String charset, String format, Integer width, Integer height) throws Exception;




}
