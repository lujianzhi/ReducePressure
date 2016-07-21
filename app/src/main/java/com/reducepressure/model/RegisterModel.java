package com.reducepressure.model;

import android.content.Context;

import com.reducepressure.contract.RegisterContract;
import com.reducepressure.entity.User;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/***
 * Created by Lawson on 2016/7/20.
 */
public class RegisterModel implements RegisterContract.RegisterModel {

    @Override
    public void sendMessageVerification(Context context, String phoneNumber, RequestSMSCodeListener requestSMSCodeListener) {
        BmobSMS.requestSMSCode(context, phoneNumber, "喽相册", requestSMSCodeListener);
    }

    @Override
    public void verifyMessageCode(Context context, String phoneNumber, String code, VerifySMSCodeListener verifySMSCodeListener) {
        BmobSMS.verifySmsCode(context, phoneNumber, code, verifySMSCodeListener);
    }

    @Override
    public void insertUserToDatabase(BmobUser user, SaveListener<User> saveListener) {
        user.signUp(saveListener);
    }
}
