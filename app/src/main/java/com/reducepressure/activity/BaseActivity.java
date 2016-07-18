package com.reducepressure.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/***
 * Created by Lawson on 2016/7/17.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {

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
        initViews();
    }

    protected abstract void initViews();

    protected abstract int getLayoutId();

}
