package com.reducepressure.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.EditText;

import com.reducepressure.MyApplication;
import com.reducepressure.R;
import com.reducepressure.contract.RegisterContract;
import com.reducepressure.entity.User;
import com.reducepressure.presenter.RegisterPresenter;
import com.reducepressure.utils.MyPermissionDialogUtils;
import com.reducepressure.utils.MyToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

public class RegisterActivity extends BaseActivity implements RegisterContract.RegisterView {

    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.verification_code)
    EditText verificationCode;
    @BindView(R.id.get_verification_code)
    Button verificationCodeButton;
    @BindView(R.id.password)
    EditText password;

    private RegisterContract.RegisterPresenter registerPresenter;

    @Override
    protected void addActivityToList() {
        MyApplication.addActivity(RegisterActivity.this);
    }

    @Override
    protected void initViewsFunction() {
        registerPresenter = new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.get_verification_code)
    void getVerificationCode() {
        changeButtonState();
    }

    @OnClick(R.id.register)
    void register() {
        registerPresenter.verifyMessageCode(phoneNumber.getText().toString(), password.getText().toString(), verificationCode.getText().toString());
    }

    private void changeButtonState() {
        registerPresenter.sendMessageVerificationForPermission(phoneNumber.getText().toString());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void startLoading() {
        super.showLoading();
    }

    @Override
    public void stopLoading() {
        super.dismissLoading();
    }

    @Override
    public Button getVerificationCodeButton() {
        return verificationCodeButton;
    }

    @Override
    public void verifySuccess() {
        registerPresenter.insertUserToDatabase();
        finish();
    }

    @Override
    public BmobUser getUser() {
        User user = MyApplication.getCurrentUser();
        user.setPassword(password.getText().toString());
        user.setUsername(phoneNumber.getText().toString());
        user.setMobilePhoneNumber(phoneNumber.getText().toString());
        return user;
    }

    @Override
    public FragmentManager viewGetSupportFragmentManager() {
        return getSupportFragmentManager();
    }

    @Override
    public int myCheckSelfPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission);
        }
        return -1;
    }

    @Override
    public boolean myShouldShowRequestPermissionRationale(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return shouldShowRequestPermissionRationale(permission);
        }
        return false;
    }

    @Override
    public void myRequestPermissions(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MyPermissionDialogUtils.READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    registerPresenter.sendMessageVerification(phoneNumber.getText().toString());
                } else {
                    MyToastUtils.showShortToast(R.string.permission_fail);
                    stopLoading();
                }
                break;

            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(RegisterActivity.this);
    }
}
