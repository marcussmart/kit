package com.dou.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by s on 2017/2/14.
 */
public class QRCodeImpl implements QRCode {



    @Override
    public byte[] encode(String content) throws Exception {
        return encode(content, QRCodeDefaultConfig.WIDTH, QRCodeDefaultConfig.HEIGHT);
    }

    @Override
    public byte[] encode(String content, Integer width, Integer height) throws Exception {
        return encode(content, QRCodeDefaultConfig.FORMAT, width, height);
    }

    @Override
    public byte[] encode(String content, String format, Integer width, Integer height) throws Exception {
        return encode(content, QRCodeDefaultConfig.CHARSET, format, width, height);
    }

    @Override
    public byte[] encode(String content, String charset, String format, Integer width, Integer height) throws Exception {

        BitMatrix bitMatrix = createBitMatrix(content, charset, width, height);
        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        MatrixToImageWriter.writeToStream(bitMatrix, format, out);
        return out.toByteArray();
    }

    @Override
    public byte[] encode(String content, InputStream logo) throws Exception {
        return encode(content, logo,  QRCodeDefaultConfig.WIDTH, QRCodeDefaultConfig.HEIGHT);
    }

    @Override
    public byte[] encode(String content, InputStream logo, Integer width, Integer height) throws Exception {
        return encode(content, logo, QRCodeDefaultConfig.FORMAT, width, height);
    }

    @Override
    public byte[] encode(String content, InputStream logo, String format, Integer width, Integer height) throws Exception {
        return encode(content, logo, QRCodeDefaultConfig.CHARSET, QRCodeDefaultConfig.FORMAT, width, height);
    }


    @Override
    public byte[] encode(String content, InputStream logo, String charset, String format, Integer width, Integer height) throws Exception {

        BitMatrix bitMatrix = createBitMatrix(content, charset, width, height);

        BufferedImage qrCodeImg = MatrixToImageWriter.toBufferedImage(bitMatrix);
        BufferedImage logoImg = ImageIO.read(logo);
        BufferedImage combine = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        //logo 在画布的坐标
        Integer drawX = (width - logoImg.getWidth()) / 2;
        Integer drawY = (height - logoImg.getHeight()) / 2;

        //二维码和logo组合成一个图片
        Graphics2D graphics = (Graphics2D) combine.getGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        graphics.drawImage(qrCodeImg, 0, 0, null);
        graphics.drawImage(logoImg, drawX, drawY, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        ImageIO.write(combine, format, out);
        return out.toByteArray();
    }

    private BitMatrix createBitMatrix(String content, String charset, Integer width, Integer height) throws WriterException {
        //生成二维码配置，设置字符集和容错率
        HashMap hint = new HashMap();
        hint.put(EncodeHintType.CHARACTER_SET, charset);
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.MARGIN, 0);

        return new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hint);
    }

}
