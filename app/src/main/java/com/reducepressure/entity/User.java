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
    private String headPortrait;
    //姓名
    private String realName;
    //昵称
    private String nickName;
    //年龄
    private String age;
    //性别  0:女  1:男
    private String sex;

    public User() {
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAge() {
        return age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
