package com.reducepressure.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import com.reducepressure.widget.MyProgressDialog;

import butterknife.ButterKnife;

/***
 * Created by Lawson on 2016/7/17.
 */
public abstract class BaseActivity extends Activity {

    protected Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        int layoutID = getLayoutId();
        if (layoutID != 0) {
            setContentView(layoutID);
        } else {
            throw new RuntimeException("未加载布局文件!");
        }
        ButterKnife.bind(this);
        addActivityToList();
        initViewsFunction();
    }

    protected void showLoading() {
        if (progressDialog == null) {
            progressDialog = new MyProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    protected void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected abstract void addActivityToList();

    protected abstract void initViewsFunction();

    protected abstract int getLayoutId();

}
