package com.mf.feel.utils;

import org.springframework.util.StringUtils;

import java.util.UUID;

public class StringUtilsPersonal {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 替换'<''>'特殊符号
     *
     * @param str 需要替换的字符串
     */
    public static String removeSpecial(String str) {
        if (!StringUtils.isEmpty(str)) {
            str = str.replaceAll("<", "&lt;");
            str = str.replaceAll(">", "&gt;");
        } else {
            str = "";
        }
        return str;
    }

    /**
     * 替换&特殊符号
     *
     * @param str 需要替换的字符串
     */
    public static String removeSpecialAnd(String str) {
        if (!StringUtils.isEmpty(str)) {
            str = str.replaceAll("&", "&amp;");
        } else {
            str = "";
        }
        return str;
    }
}
