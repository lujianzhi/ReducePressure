package com.reducepressure.presenter;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.reducepressure.MyApplication;
import com.reducepressure.R;
import com.reducepressure.contract.MainContract;
import com.reducepressure.entity.User;
import com.reducepressure.model.MainModel;
import com.reducepressure.utils.ImagePickerPicLoder;
import com.reducepressure.utils.MyCommonUtils;
import com.reducepressure.utils.MyLogUtils;
import com.reducepressure.utils.MyToastUtils;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/***
 * Created by Lawson on 2016/7/22.
 */
public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView mainView;
    private MainContract.MainModel mainModel;

    public MainPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
        this.mainModel = new MainModel();
    }

    @Override
    public void selectUserHeadPortrait(Activity activity) {
        ImageConfig imageConfig
                = new ImageConfig.Builder(new ImagePickerPicLoder())
                .steepToolBarColor(mainView.getContext().getResources().getColor(R.color.colorPrimaryDark))
                .titleBgColor(mainView.getContext().getResources().getColor(R.color.colorPrimaryDark))
                .titleSubmitTextColor(mainView.getContext().getResources().getColor(R.color.text_white))
                .titleTextColor(mainView.getContext().getResources().getColor(R.color.text_white))
                // 开启单选   （默认为多选）
                .singleSelect()
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath("/ReducePressure/Pictures")
                //开启自定义截图
                .crop(1, 1, 500, 500)
                .build();


        ImageSelector.open(activity, imageConfig);   // 开启图片选择器
    }

    @Override
    public void uploadUserHeadPortrait(String path, final ImageView headPortrait) {

        final File userPortraitFile = new File(path);
        final BmobFile userPortraitBmobFile = new BmobFile(userPortraitFile);

        userPortraitBmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                Glide.with(mainView.getContext()).load(userPortraitFile).into(headPortrait);
                mainModel.saveUrlToTableUser(userPortraitBmobFile.getFileUrl());
                MyToastUtils.showShortToast("头像上传成功");
            }
        });

    }

    @Override
    public void updateUserInfo(String nickName, String realName, String age, String sexType) {
        mainView.startLoading();
        if (MyCommonUtils.isNullStr(nickName) ||
                MyCommonUtils.isNullStr(realName) ||
                MyCommonUtils.isNullStr(age) ||
                MyCommonUtils.isNullStr(sexType)) {
            MyToastUtils.showShortToast("请填写完整信息");
            mainView.stopLoading();
        } else {
            mainModel.updateUserInfo(nickName, realName, age, sexType, new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    mainView.stopLoading();
                    if (e == null) {
                        MyToastUtils.showShortToast("修改成功");
                        mainView.updateUIAfterEditUserInfo();
                        mainView.dismissDialog();
                    } else {
                        MyToastUtils.showShortToast("修改失败");
                        MyLogUtils.e(e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void updatePassword(String oldPassword, final String newPassword, String newPasswordAgain) {
        mainView.startLoading();
        if (MyCommonUtils.isNullStr(oldPassword) ||
                MyCommonUtils.isNullStr(newPassword) ||
                MyCommonUtils.isNullStr(newPasswordAgain)) {
            MyToastUtils.showShortToast("请填写完整信息");
            mainView.stopLoading();
        } else if (!newPassword.equals(newPasswordAgain)) {
            MyToastUtils.showShortToast("两次密码输入不一致");
            mainView.stopLoading();
        } else {
            mainModel.updatePassword(oldPassword, newPassword, new FindListener<BmobUser>() {
                @Override
                public void done(List<BmobUser> list, BmobException e) {
                    if (e == null && list.size() != 0) {
                        User user = MyApplication.getCurrentUser();
                        user.setPassword(newPassword);
                        user.update(user.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                mainView.stopLoading();
                                if (e == null) {
                                    MyToastUtils.showShortToast("修改成功");
                                    mainView.dismissDialog();
                                } else {
                                    MyToastUtils.showShortToast("修改失败");
                                    MyLogUtils.e(e.getMessage());
                                }
                            }
                        });
                    } else {
                        mainView.stopLoading();
                        MyToastUtils.showShortToast("密码错误");
                        if (e != null) {
                            MyLogUtils.e(e.getMessage());
                        }
                    }
                }
            });
        }
    }

}
