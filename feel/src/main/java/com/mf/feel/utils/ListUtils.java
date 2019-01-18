package com.mf.feel.utils;

import java.util.Collection;

public class ListUtils {
    public static Boolean isEmpty(Collection list) {
        return list == null || list.size() == 0;
    }

    public static Boolean isNotEmpty(Collection list) {
        return !isEmpty(list);
    }
}
