package com.reducepressure.model;

import com.reducepressure.contract.LoginContract;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/***
 * Created by Lawson on 2016/7/21.
 */
public class LoginModel implements LoginContract.LoginModel {

    @Override
    public void login(BmobUser user, SaveListener<BmobUser> saveListener) {
        user.login(saveListener);
    }
}
