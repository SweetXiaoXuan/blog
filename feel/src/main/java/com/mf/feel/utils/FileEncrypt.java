package com.mf.feel.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 文件加密
 */
@Slf4j
public class FileEncrypt {

    public static ByteArrayOutputStream transfer(InputStream inputStream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int b;
        while ((b = bis.read()) != -1) {
            outputStream.write(b ^ 123);
        }

        bis.close();
        return outputStream;
    }

    /**
     * 解密
     *
     * @throws IOException
     */
    public static ByteArrayOutputStream decrypt(InputStream inputStream) throws IOException {
        return transfer(inputStream);
    }

    /**
     * 加密
     *
     * @throws IOException
     */
    public static ByteArrayOutputStream encrypt(InputStream inputStream) throws IOException {
        return transfer(inputStream);
    }

    /**
     * 加解密文件夹里面的文件
     *
     * @param path 文件夹路径
     */
    public static void operationFolder(String path) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File listFile : files) {
                if (listFile.isDirectory()) {
                    operationFolder(listFile.getPath());
                    continue;
                }
                try (FileInputStream fileInputStream = new FileInputStream(listFile);
                     ByteArrayOutputStream encrypt = encrypt(fileInputStream)) {
                    FileOutputStream outputStream1 = new FileOutputStream(new File(listFile.getPath()));
                    encrypt.writeTo(outputStream1);
                } catch (IOException e) {
                    log.error("加解密文件夹失败");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        operationFolder("d:\\data\\upload\\tmp\\exportPanel\\20181217101271\\");
//        FileInputStream fileInputStream = new FileInputStream(new File("d:\\data\\upload\\secret\\1\\190\\211\\284\\4190\\de93933d47ce4f10b48fce9225cf80f9040.jpg"));
//        ByteArrayOutputStream encrypt = encrypt(fileInputStream);
//        FileOutputStream outputStream1 = new FileOutputStream(new File("d:\\data\\upload\\secret\\1\\190\\211\\284\\4190\\de93933d47ce4f10b48fce9225cf80f9040Copy.jpg"));
//        encrypt.writeTo(outputStream1);
//
//
//        FileInputStream fileInputStreamOut = new FileInputStream(new File("d:\\data\\upload\\secret\\1\\190\\211\\284\\4190\\de93933d47ce4f10b48fce9225cf80f9040Copy.jpg"));
//        ByteArrayOutputStream decrypt = decrypt(fileInputStreamOut);
//        FileOutputStream out = new FileOutputStream(new File("d:\\data\\upload\\secret\\1\\190\\211\\284\\4190\\de93933d47ce4f10b48fce9225cf80f9040CopyCopy.jpg"));
//        decrypt.writeTo(out);
//        outputStream1.close();
//        out.close();
    }
}