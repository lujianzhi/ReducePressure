package com.reducepressure.contract;

import com.reducepressure.model.IBaseModel;
import com.reducepressure.presenter.IBasePresenter;
import com.reducepressure.view.IBaseView;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/***
 * Created by Lawson on 2016/7/21.
 */
public interface LoginContract {

    interface LoginView extends IBaseView {

        /**
         * 进入主界面
         */
        void goToMainActivity();
    }

    interface LoginPresenter extends IBasePresenter {

        /**
         * 登陆
         */
        void login(BmobUser user);
    }


    interface LoginModel extends IBaseModel {

        /**
         * 登陆
         */
        void login(BmobUser user, SaveListener<BmobUser> saveListener);
    }

}
