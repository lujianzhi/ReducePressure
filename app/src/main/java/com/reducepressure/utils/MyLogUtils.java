package com.reducepressure.utils;

import android.util.Log;

/***
 * Created by Lawson on 2016/7/20.
 */
public class MyLogUtils {

    //打印日志的开关
    private static boolean showLog = true;

    public static void i(String tag, String i) {
        if (showLog) {
            Log.i(tag, i);
        }
    }

    public static void i(String i) {
        if (showLog) {
            Log.i("lawson", i);
        }
    }

    public static void e(String tag, String e) {
        if (showLog) {
            Log.e(tag, e);
        }
    }

    public static void e(String e) {
        if (showLog) {
            Log.e("lawson", e);
        }
    }

}
