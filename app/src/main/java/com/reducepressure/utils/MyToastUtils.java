package com.reducepressure.utils;

import android.content.Context;
import android.widget.Toast;

/***
 * Created by Lawson on 2016/7/20.
 */
public class MyToastUtils {

    private static Context context;

    public static void initMyToastUtils(Context mContext) {
        context = mContext;
    }

    public static void showLongToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
