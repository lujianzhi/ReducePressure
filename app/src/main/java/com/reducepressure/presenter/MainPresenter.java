package com.reducepressure.presenter;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.reducepressure.R;
import com.reducepressure.contract.MainContract;
import com.reducepressure.model.MainModel;
import com.reducepressure.utils.ImagePickerPicLoder;
import com.reducepressure.utils.MyToastUtils;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
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
}
