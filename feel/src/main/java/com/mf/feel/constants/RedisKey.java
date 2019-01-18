package com.mf.feel.constants;

/**
 * redis缓存
 */
public class RedisKey {
    /**
     * 用户键前缀
     */
    public static final String USER_PRE = "user";

    public static String getColonKey(String key) {
        return key + "::";
    }
}