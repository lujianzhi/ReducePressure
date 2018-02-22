package com.reducepressure.utils;

/***
 * Created by Lawson on 2016/7/24.
 */
public class MyCommonUtils {

    /**
     * 检测是否为空
     */
    public static boolean isNullStr(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 检测是否为空
     */
    public static boolean isNotNull(Object object) {
        return object != null;
    }

}
