package com.reducepressure.contract;

import android.app.Activity;
import android.widget.ImageView;

import com.reducepressure.model.IBaseModel;
import com.reducepressure.presenter.IBasePresenter;
import com.reducepressure.view.IBaseView;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/***
 * Created by Lawson on 2016/7/22.
 */
public interface MainContract {

    interface MainView extends IBaseView {

        /**
         * 修改完成以后更新UI
         */
        void updateUIAfterEditUserInfo();

        /**
         * 关闭dialog
         */
        void dismissDialog();

    }

    interface MainPresenter extends IBasePresenter {

        /**
         * 选择用户头像
         */
        void selectUserHeadPortrait(Activity activity);

        /**
         * 上传头像
         */
        void uploadUserHeadPortrait(String path, ImageView headPortrait);

        /**
         * 修改资料
         */
        void updateUserInfo(String nickName, String realName, String age, String sexType);

        /**
         * 修改密码
         */
        void updatePassword(String oldPassword, String newPassword, String newPasswordAgain);

        /**
         * 用户反馈
         */
        void userFeedback(String content);
    }

    interface MainModel extends IBaseModel {

        /**
         * 将头像url存入_User表中
         */
        void saveUrlToTableUser(String url);

        /**
         * 保存修改的用户数据
         */
        void updateUserInfo(String nickName, String realName, String age, String sexType, UpdateListener updateListener);

        /**
         * 修改密码
         */
        void updatePassword(String oldPassword, String newPassword, FindListener<BmobUser> findListener);

        /**
         * 用户反馈
         */
        void userFeedback(String content, SaveListener<String> saveListener);
    }

}
