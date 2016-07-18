package com.reducepressure.net;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.reducepressure.MyApplication;

import java.util.Map;

/***
 * Created by Lawson on 2016/7/17.
 */
public class VolleyRequestUtil {

    /**
     * Post请求
     *
     * @param tag 请求的标记
     * @param url 请求的网址
     */
    public static void sendPostRequest(String tag, String url, final Map<String, String> params, VolleyListenerInterface volleyListenerInterface) {
        MyApplication.getRequestQueue().cancelAll(tag);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, volleyListenerInterface.successResponseListener(), volleyListenerInterface.errorResponseListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(stringRequest);
        MyApplication.getRequestQueue().start();
    }

}
