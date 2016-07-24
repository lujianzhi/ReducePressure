package com.reducepressure.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reducepressure.MyApplication;
import com.reducepressure.R;
import com.reducepressure.contract.MainContract;
import com.reducepressure.entity.User;
import com.reducepressure.presenter.MainPresenter;
import com.reducepressure.widget.MyUpdatePasswordDialog;
import com.reducepressure.widget.MyUpdateUserInfoDialog;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/***
 * Created by Lawson on 2016/7/17.
 */
public class MainActivity extends BaseActivity implements MainContract.MainView, View.OnClickListener {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tableLayout)
    TabLayout tableLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    private ImageView headPortrait;
    private BottomSheetDialog userCenterDialog;

    private MainContract.MainPresenter mainPresenter;
    private User user = MyApplication.getCurrentUser();

    @Override
    protected void addActivityToList() {
        MyApplication.addActivity(MainActivity.this);
    }

    @Override
    protected void initViewsFunction() {

        mainPresenter = new MainPresenter(this);

        initNavigationView();

        toolBar.setTitle(R.string.app_name);
        toolBar.setTitleTextColor(getResources().getColor(R.color.text_white));

    }

    private TextView navigationUserName;

    private void initNavigationView() {
        View view = navigationView.getHeaderView(0);
        navigationUserName = (TextView) view.findViewById(R.id.user_name);
        navigationUserName.setText(user.getNickName());

        headPortrait = (ImageView) view.findViewById(R.id.head_portrait);
        headPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.selectUserHeadPortrait(MainActivity.this);
            }
        });

        String headPortraitStr = user.getHeadPortrait();
        if (headPortraitStr != null && !"".equals(headPortraitStr)) {
            Glide.with(getContext()).load(headPortraitStr).into(headPortrait);
        }

        createUserCenterDialog();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit_info:
                        drawerLayout.closeDrawer(navigationView);
                        if (userCenterDialog.isShowing()) {
                            userCenterDialog.dismiss();
                        } else {
                            userCenterDialog.show();
                        }
                        break;
                }
                return false;
            }
        });

    }

    private TextView userName;

    private void createUserCenterDialog() {
        userCenterDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.user_center_layout, null, false);
        userCenterDialog.setContentView(view);

        CircleImageView headPortrait = (CircleImageView) view.findViewById(R.id.head_portrait);
        userName = (TextView) view.findViewById(R.id.user_name);
        TextView userPhone = (TextView) view.findViewById(R.id.user_phone);
        LinearLayout editInfo = (LinearLayout) view.findViewById(R.id.edit_info);
        LinearLayout editPassword = (LinearLayout) view.findViewById(R.id.edit_password);

        userName.setText(user.getNickName());
        userPhone.setText(user.getMobilePhoneNumber());
        Glide.with(this).load(user.getHeadPortrait()).into(headPortrait);

        editInfo.setOnClickListener(this);
        editPassword.setOnClickListener(this);

        View view_ = userCenterDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        if (view_ != null) {
            final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view_);
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        userCenterDialog.dismiss();
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

            for (String path : pathList) {
                mainPresenter.uploadUserHeadPortrait(path, headPortrait);
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_info:
                showUpdateInfoDialog();
                break;
            case R.id.edit_password:
                showUpdatePasswordDialog();
                break;
        }
    }

    private MyUpdateUserInfoDialog editUserInfoDialog;
    private MyUpdatePasswordDialog updatePasswordDialog;

    private void showUpdateInfoDialog() {
        editUserInfoDialog = new MyUpdateUserInfoDialog(this, mainPresenter);
        editUserInfoDialog.show();
        userCenterDialog.dismiss();
    }

    private void showUpdatePasswordDialog() {
        updatePasswordDialog = new MyUpdatePasswordDialog(this, mainPresenter);
        updatePasswordDialog.show();
        userCenterDialog.dismiss();
    }

    @Override
    public void updateUIAfterEditUserInfo() {
        userName.setText(user.getNickName());
        navigationUserName.setText(user.getNickName());
    }

    @Override
    public void dismissDialog() {
        if (editUserInfoDialog != null && editUserInfoDialog.isShowing()) {
            editUserInfoDialog.dismiss();
        }
        if (updatePasswordDialog != null && updatePasswordDialog.isShowing()) {
            updatePasswordDialog.dismiss();
        }
    }
}
