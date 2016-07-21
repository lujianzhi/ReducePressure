package com.reducepressure.presenter;

import com.reducepressure.contract.LoginContract;
import com.reducepressure.model.LoginModel;
import com.reducepressure.utils.MyLogUtils;
import com.reducepressure.utils.MyToastUtils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/***
 * Created by Lawson on 2016/7/21.
 */
public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView loginView;
    private LoginContract.LoginModel loginModel;

    public LoginPresenter(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModel();
    }

    @Override
    public void login(BmobUser user) {
        loginView.startLoading();
        loginModel.login(user, new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser user, BmobException e) {
                loginView.stopLoading();
                if (e == null) {
                    loginView.goToMainActivity();
                } else {
                    MyLogUtils.e(e.getMessage());
                    MyToastUtils.showShortToast("用户名或密码错误");
                }
            }
        });
    }
}
