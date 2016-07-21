package com.reducepressure.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.reducepressure.MyApplication;
import com.reducepressure.R;
import com.reducepressure.contract.LoginContract;
import com.reducepressure.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {

    @BindView(R.id.phone_layout)
    TextInputLayout phoneLayout;
    @BindView(R.id.password_layout)
    TextInputLayout passwordLayout;

    private EditText phone;
    private EditText password;

    private LoginContract.LoginPresenter loginPresenter;

    @Override
    protected void addActivityToList() {
        MyApplication.addActivity(LoginActivity.this);
    }

    @Override
    protected void initViewsFunction() {

        loginPresenter = new LoginPresenter(this);

        phone = phoneLayout.getEditText();
        password = passwordLayout.getEditText();
        if (phone != null) {
            phone.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 11) {
                        phoneLayout.setErrorEnabled(true);
                        phoneLayout.setError("请输入正确长度的手机号");
                    } else {
                        phoneLayout.setError("");
                        phoneLayout.setErrorEnabled(false);
                    }
                }
            });
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.login)
    void login() {
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(phone.getText().toString());
        bmobUser.setPassword(password.getText().toString());
        loginPresenter.login(bmobUser);
    }

    @OnClick(R.id.register)
    void register() {
        startActivity(new Intent(this, RegisterActivity.class));
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
    public void goToMainActivity() {
        MyApplication.removeActivity(LoginActivity.this);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
