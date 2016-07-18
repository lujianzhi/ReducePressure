package com.reducepressure;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.reducepressure.utils.MyConstants;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/***
 * Created by Lawson on 2016/7/17.
 */
public class MyApplication extends Application {

    public static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化BmobSDK
        BmobConfig bmobConfig = new BmobConfig.Builder(getApplicationContext())
                .setApplicationId(MyConstants.BMOB_MODE_APPLICATION_ID)
                .setConnectTimeout(20)
                .setUploadBlockSize(1024 * 1024)
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(bmobConfig);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
