package com.reducepressure.entity;

import cn.bmob.v3.BmobObject;

/***
 * Created by Lawson on 2016/7/24.
 */
public class FeedBack extends BmobObject {

    private String userId;
    private String content;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
