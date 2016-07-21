package com.reducepressure.presenter;

import com.reducepressure.contract.RegisterContract;
import com.reducepressure.entity.User;
import com.reducepressure.model.RegisterModel;
import com.reducepressure.utils.MyCountDownTimer;
import com.reducepressure.utils.MyLogUtils;
import com.reducepressure.utils.MyToastUtils;

import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

/***
 * Created by Lawson on 2016/7/19.
 */
public class RegisterPresenter implements RegisterContract.RegisterPresenter {

    private RegisterContract.RegisterView registerView;
    private RegisterContract.RegisterModel registerModel;

    public RegisterPresenter(RegisterContract.RegisterView registerView) {
        this.registerView = registerView;
        this.registerModel = new RegisterModel();
    }

    @Override
    public void sendMessageVerification(String phoneNumber) {
        registerView.startLoading();
        if ("".equals(phoneNumber)) {
            registerView.stopLoading();
            MyToastUtils.showShortToast("请输入手机号");
        } else {
            MyCountDownTimer myCountDownTimer = new MyCountDownTimer(registerView.getVerificationCodeButton(), 60000, 1000);
            myCountDownTimer.start();
            registerModel.sendMessageVerification(registerView.getContext(), phoneNumber, new RequestSMSCodeListener() {
                @Override
                public void done(Integer integer, BmobException e) {
                    registerView.stopLoading();
                    if (e == null) {
                        MyToastUtils.showShortToast("短信发送成功");
                        MyLogUtils.i("短信id:" + integer);
                    } else {
                        MyToastUtils.showShortToast("短信发送失败");
                        MyLogUtils.e("短信发送失败 : " + e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void verifyMessageCode(String phoneNumber, String password, String code) {
        registerView.startLoading();
        if ("".equals(phoneNumber) && "".equals(code) && "".equals(password)) {
            registerView.stopLoading();
            MyToastUtils.showShortToast("请输入完整信息");
        } else {
            registerModel.verifyMessageCode(registerView.getContext(), phoneNumber, code, new VerifySMSCodeListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        registerView.verifySuccess();
                    } else {
                        registerView.stopLoading();
                        MyToastUtils.showShortToast("短信验证失败");
                        MyLogUtils.e("短信验证失败 : " + e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void insertUserToDatabase() {
        registerModel.insertUserToDatabase(registerView.getUser(), new SaveListener<User>() {
            @Override
            public void done(User user, cn.bmob.v3.exception.BmobException e) {
                if (e == null) {
                    registerView.stopLoading();
                    MyToastUtils.showShortToast("注册成功");
                    MyLogUtils.i(user.toString());
                } else {
                    MyLogUtils.e(e.getMessage());
                }
            }
        });
    }
}
