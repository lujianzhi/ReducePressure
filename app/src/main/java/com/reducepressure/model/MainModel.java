package com.reducepressure.model;

import com.reducepressure.MyApplication;
import com.reducepressure.contract.MainContract;
import com.reducepressure.entity.FeedBack;
import com.reducepressure.entity.User;
import com.reducepressure.utils.MyLogUtils;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/***
 * Created by Lawson on 2016/7/22.
 */
public class MainModel implements MainContract.MainModel {

    private User user = MyApplication.getCurrentUser();

    @Override
    public void saveUrlToTableUser(String url) {

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

    @Override
    public void updateUserInfo(String nickName, String realName, String age, String sexType, UpdateListener updateListener) {
        user.setNickName(nickName);
        user.setRealName(realName);
        user.setAge(age);
        user.setSex(sexType);
        user.update(user.getObjectId(), updateListener);
    }

    @Override
    public void updatePassword(String oldPassword, final String newPassword, FindListener<BmobUser> findListener) {
        BmobQuery<BmobUser> query = new BmobQuery<>();
        query.addWhereEqualTo("password", oldPassword);
        query.addWhereEqualTo("objectId", user.getObjectId());
        query.findObjects(findListener);
    }

    @Override
    public void userFeedback(String content, SaveListener<String> saveListener) {
        FeedBack feedBack = new FeedBack();
        feedBack.setContent(content);
        feedBack.setUserId(user.getObjectId());
        feedBack.save(saveListener);
    }
}
