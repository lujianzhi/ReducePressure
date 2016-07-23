package com.reducepressure.view;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import com.reducepressure.MyApplication;
import com.reducepressure.R;
import com.reducepressure.contract.RegisterContract;
import com.reducepressure.entity.User;
import com.reducepressure.presenter.RegisterPresenter;

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
        registerPresenter.sendMessageVerification(phoneNumber.getText().toString());
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
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.removeActivity(RegisterActivity.this);
    }
}
