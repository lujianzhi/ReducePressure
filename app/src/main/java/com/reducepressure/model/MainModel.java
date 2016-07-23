package com.reducepressure.model;

import com.reducepressure.MyApplication;
import com.reducepressure.contract.MainContract;
import com.reducepressure.entity.User;
import com.reducepressure.utils.MyLogUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/***
 * Created by Lawson on 2016/7/22.
 */
public class MainModel implements MainContract.MainModel {

    @Override
    public void saveUrlToTableUser(String url) {

        User user = MyApplication.getCurrentUser();
        user.setHeadPortrait(url);
        user.update(user.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    MyLogUtils.i("头像url保存成功");
                } else {
                    MyLogUtils.e(e.getMessage());
                }
            }
        });
    }
}
