package com.reducepressure;

import android.app.Activity;
import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.reducepressure.utils.MyConstants;
import com.reducepressure.utils.MyToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/***
 * Created by Lawson on 2016/7/17.
 */
public class MyApplication extends Application {

    public static RequestQueue requestQueue;
    public static List<Activity> activityList = new ArrayList<>();

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
        //初始化短信模块
        BmobSMS.initialize(getApplicationContext(), MyConstants.BMOB_MODE_APPLICATION_ID);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        MyToastUtils.initMyToastUtils(getApplicationContext());
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }
}
