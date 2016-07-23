package com.reducepressure.contract;

import android.app.Activity;
import android.widget.ImageView;

import com.reducepressure.model.IBaseModel;
import com.reducepressure.presenter.IBasePresenter;
import com.reducepressure.view.IBaseView;

/***
 * Created by Lawson on 2016/7/22.
 */
public interface MainContract {

    interface MainView extends IBaseView {

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
    }

    interface MainModel extends IBaseModel {

        /**
         * 将头像url存入_User表中
         */
        void saveUrlToTableUser(String url);
    }

}
