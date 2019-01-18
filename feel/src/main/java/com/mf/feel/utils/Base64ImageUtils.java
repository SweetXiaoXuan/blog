package com.mf.feel.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Base64ImageUtils {
    /**
     * 将网络图片进行Base64位编码
     *
     * @param imageUrl 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(URL imageUrl) throws Exception {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串


        //之前的转码直接传给前端需要加base64头信息
//        String encode = "data:image/jpg;base64," + encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
//        return encode;
    }

    /**
     * 将本地图片进行Base64位编码
     *
     * @param imageFile 图片的url路径，如F:/.....xx.jpg
     * @return
     */
    public static String encodeImgageToBase64(File imageFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64 base64编码的图片信息
     * @return
     */
    public static void decodeBase64ToImage(String base64, String path,
                                           String imgName) {

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            FileOutputStream write = new FileOutputStream(new File(path
                    + imgName));
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64 base64编码的图片信息
     * @return
     */
    public static byte[] decodeBase64ToByte(String base64) {
        byte[] decoderBytes = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            decoderBytes = decoder.decodeBuffer(base64);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decoderBytes;
    }

    public static void main(String[] args) throws Exception {
        URL url = null;
        // FileOutputStream write = new FileOutputStream(new File("D:\\Documents\\WeChat Files\\C_o-YJF\\123123.png"));
        String encoderStr = Base64ImageUtils.encodeImgageToBase64(new File("D:\\Documents\\WeChat Files\\C_o-YJF\\111111.png"));
        System.out.print(encoderStr);


//        String encoderStr = Base64ImageUtils.GetUrlImageToBase64(url.toString());
//        System.out.println(encoderStr);
        //    Base64ImageUtils.decodeBase64ToImage(encoderStr, "/Users/spicery/work", "111");
//        Base64ImageUtils.decodeBase64ToImage(encoderStr, "E:/", "football.jpg");

    }
}

