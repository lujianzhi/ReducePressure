package com.reducepressure.activity;

import android.os.Bundle;

import com.reducepressure.MyApplication;
import com.reducepressure.R;

/***
 * Created by Lawson on 2016/7/17.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void addActivityToList() {
        MyApplication.addActivity(MainActivity.this);
    }

    @Override
    protected void initViewsFunction() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
