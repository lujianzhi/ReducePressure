package com.reducepressure.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/***
 * Created by Lawson on 2016/7/17.
 */
public abstract class VolleyListenerInterface {

    private Response.Listener<String> mSuccessListener;
    private Response.ErrorListener mErrorListener;

    public VolleyListenerInterface(Response.Listener<String> mSuccessListener, Response.ErrorListener mErrorListener) {
        this.mSuccessListener = mSuccessListener;
        this.mErrorListener = mErrorListener;
    }

    /**
     * 请求成功时的回调函数
     */
    public abstract void onSuccess();

    /**
     * 请求失败时的回调函数
     */
    public abstract void onError();

    /**
     * 创建请求成功的事件监听
     */
    public Response.Listener<String> successResponseListener() {
        mSuccessListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onSuccess();
            }
        };
        return mSuccessListener;
    }

    /**
     * 创建请求失败的事件监听
     */
    public Response.ErrorListener errorResponseListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError();
            }
        };
        return mErrorListener;
    }

}
