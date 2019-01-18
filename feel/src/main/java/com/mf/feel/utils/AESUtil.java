package com.mf.feel.utils;


import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class AESUtil {
    public AESUtil() {
    }

    /**
     * 加密函数
     *
     * @param content 加密的内容
     * @param strKey  密钥
     * @return 返回二进制字符数组
     * @throws Exception
     */
    public static byte[] enCrypt(String content, String strKey) throws Exception {
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;
        String str = content;

        keygen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(strKey.getBytes());
        keygen.init(128, secureRandom);

        SecretKeySpec securekey = new SecretKeySpec(strKey.getBytes(), "AES");

        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");

        c.init(Cipher.ENCRYPT_MODE, desKey);

        cByte = c.doFinal(str.getBytes(StandardCharsets.UTF_8));

        return cByte;
    }

    /**
     * 解密函数
     *
     * @param src    加密过的二进制字符数组
     * @param strKey 密钥
     * @return
     * @throws Exception
     */
    public static String deCrypt(byte[] src, String strKey) throws Exception {
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;

        keygen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(strKey.getBytes());
        keygen.init(128, secureRandom);

        SecretKeySpec securekey = new SecretKeySpec(strKey.getBytes(), "AES");

        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");

        c.init(Cipher.DECRYPT_MODE, desKey);


        cByte = c.doFinal(src);

        return new String(cByte, StandardCharsets.UTF_8);
    }


    /**
     * 2进制转化成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /*
     * 加密url参数
     * */
    public static String encodeParams(String content, String key) {
        String encodeParams = "";
        if (StringUtils.isEmpty(content)) {
            return encodeParams;
        }
        try {
            byte[] encodeByte = enCrypt(content, key);
            encodeParams = parseByte2HexStr(encodeByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeParams;
    }

    /*
     * 解密加密的url参数
     * */
    public static String decodeParam(String content, String key) {
        String decodeParams = "";
        if (StringUtils.isEmpty(content)) {
            return decodeParams;
        }
        byte[] decodeByte = parseHexStr2Byte(content);
        try {
            decodeParams = deCrypt(decodeByte, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeParams;
    }

    public static void main(String[] args) {
        System.out.println(encodeParams("123456", "dsfasdfasdfaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        System.out.println(decodeParam("6366CF60CC5EEB96520B8CE1331602D3", "dsfasdfasdfaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }
}
