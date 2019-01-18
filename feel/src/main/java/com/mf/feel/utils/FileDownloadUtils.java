package com.mf.feel.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileDownloadUtils {

    /**
     * 输出文件流供下载
     *
     * @param file         文件
     * @param downloadName 下载的文件名
     * @param response     response
     */
    public static void outPutFileForDownload(File file, String downloadName, HttpServletResponse response) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            outPutFileForDownload(fileInputStream, downloadName, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出文件流供下载
     *
     * @param inputStream  文件流
     * @param downloadName 下载的文件名
     * @param response     response
     */
    public static void outPutFileForDownload(InputStream inputStream, String downloadName, HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + downloadName);
        byte[] buff = new byte[1024];
        try (OutputStream os = response.getOutputStream();
             BufferedInputStream bis = new BufferedInputStream(inputStream)) {
            response.setContentLengthLong(inputStream.available());
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
