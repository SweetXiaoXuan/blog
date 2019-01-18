package com.mf.feel.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StreamUtil {
    /**
     * 输入流转输出流
     *
     * @param out
     * @return ByteArrayInputStream
     * @throws Exception
     */
    public static ByteArrayInputStream parse(OutputStream out) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream swapStream = null;
        try {
            baos = (ByteArrayOutputStream) out;
            swapStream = new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                baos.close();
                swapStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return swapStream;
    }
}
