package com.reducepressure.utils;

import com.reducepressure.contract.RegisterContract;
import com.reducepressure.ui.MyPermissionDialog;

/**
 * Created by Ian.Lu on 2018/2/22.
 * Project : ReducePressure
 */

public class MyPermissionDialogUtils {

    public static final int READ_PHONE_STATE = 0;

    public static void showDialog(final RegisterContract.RegisterView registerView, final String... permissionString) {
        if (!registerView.myShouldShowRequestPermissionRationale(permissionString[0])) {
            MyPermissionDialog.newInstance(new MyPermissionDialog.DialogButtonListener() {
                @Override
                public void onConfirm() {
                    registerView.myRequestPermissions(permissionString, READ_PHONE_STATE);
                    registerView.stopLoading();
                }

                @Override
                public void onCancel() {
                    MyToastUtils.showShortToast("获取权限失败，获取不了验证码");
                    registerView.stopLoading();
                }
            }, "发送短信").show(registerView.viewGetSupportFragmentManager(), "");
        } else {
            registerView.myRequestPermissions(permissionString, READ_PHONE_STATE);
            registerView.stopLoading();
        }
    }

}
