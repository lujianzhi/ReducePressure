package com.reducepressure.entity;

import cn.bmob.v3.BmobUser;

/***
 * 父类中已有的参数
 * 用户ID objectId
 * 用户名 username
 * 密码 password
 * 昵称 nickName
 * 邮箱 email
 * 手机号 mobilePhoneNumber
 * Created by Lawson on 2016/7/20.
 */
public class User extends BmobUser {

    //头像
    private String picUrl;

    public User() {
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
