package com.reducepressure.contract;

import android.content.Context;
import android.widget.Button;

import com.reducepressure.entity.User;
import com.reducepressure.model.IBaseModel;
import com.reducepressure.presenter.IBasePresenter;
import com.reducepressure.view.IBaseView;

import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/***
 * Created by Lawson on 2016/7/19.
 */
public interface RegisterContract {

    interface RegisterView extends IBaseView {

        /**
         * 获取按钮
         */
        Button getVerificationCodeButton();

        /**
         * 验证成功跳转界面
         */
        void verifySuccess();

        /**
         * 获取用户信息
         */
        BmobUser getUser();
    }

    interface RegisterPresenter extends IBasePresenter {

        /**
         * 发送短信验证请求
         */
        void sendMessageVerification(String phoneNumber);

        /**
         * 验证验证码
         */
        void verifyMessageCode(String phoneNumber, String password, String code);

        /**
         * 写入数据库
         */
        void insertUserToDatabase();

    }

    interface RegisterModel extends IBaseModel {

        /**
         * 发送短信验证请求
         */
        void sendMessageVerification(Context context, String phoneNumber, RequestSMSCodeListener requestSMSCodeListener);

        /**
         * 验证验证码
         */
        void verifyMessageCode(Context context, String phoneNumber, String code, VerifySMSCodeListener verifySMSCodeListener);

        /**
         * 实际操作数据库
         */
        void insertUserToDatabase(BmobUser user, SaveListener<User> saveListener);
    }

}
