package com.mf.feel.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class Base64Util {
    /**
     * 获取文件类型
     *
     * @param base64str base64字符串
     * @return
     */
    public static String getFileType(String base64str) {
        int index = base64str.indexOf("/");
        int end = base64str.indexOf(";");
        if (index != -1 && end != -1) {
            return base64str.substring(index + 1, end);
        }
        return null;
    }

    /**
     * 移除文件头
     *
     * @param base64str base64字符串
     * @return
     */
    public static String removeHead(String base64str) {
        int index = base64str.indexOf(",");
        if (base64str.contains("data:") && index != -1) {
            base64str = base64str.substring(index + 1);
        }
        return base64str;
    }

    public static ByteArrayOutputStream GenerateImage(String base64str) throws IOException {

        ByteArrayOutputStream out = null;
        //对字节数组字符串进行Base64解码并生成图片
        if (base64str == null) //图像数据为空
            return null;

        // System.out.println("开始解码");
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64解码
        byte[] b = decoder.decodeBuffer(base64str);
        //  System.out.println("解码完成");
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {//调整异常数据
                b[i] += 256;
            }
        }
        // System.out.println("开始生成图片");
        //生成jpeg图片
        out = new ByteArrayOutputStream();
        out.write(b);
        return out;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("d:\\data\\tmp\\upload\\a.txt");
        InputStream fileInputStream = new FileInputStream(file);
//        byte[] bytes = new byte[1024];
//        int read = 0;
//        while ((read = fileInputStream.read(bytes)) != 0) {
//
//        }
        String im = GetImageStr("d:\\data\\tmp\\upload\\source.jpg");
        BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        String imagestr = buffer.toString();


        imagestr = imagestr.replace("data:image/png;base64,", "");
//        GenerateImage2(imagestr);
        ByteArrayOutputStream outputStream = Base64Util.GenerateImage(imagestr);
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\data\\tmp\\upload\\aca.png");
        outputStream.writeTo(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        outputStream.close();

    }

    public static String GetImageStr(String imgFile) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
}
