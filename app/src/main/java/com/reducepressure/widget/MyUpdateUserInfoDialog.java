package com.reducepressure.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.reducepressure.MyApplication;
import com.reducepressure.R;
import com.reducepressure.contract.MainContract;
import com.reducepressure.entity.User;


/***
 * Created by Lawson on 2016/7/20.
 */
public class MyUpdateUserInfoDialog extends Dialog {

    /**
     * 0:女
     * 1:男
     */
    private String sexType;

    private View dialogView;
    private EditText nick_name;
    private EditText real_name;
    private EditText user_age;
    private RadioGroup sex;
    private Button confirm;
    private Button cancel;
    private User user;

    public MyUpdateUserInfoDialog(Context context, final MainContract.MainPresenter mainPresenter) {
        super(context, R.style.emptyDialog);
        user = MyApplication.getCurrentUser();
        dialogView = View.inflate(context, R.layout.dialog_update_user_info_layout, null);
        nick_name = (EditText) dialogView.findViewById(R.id.nick_name);
        real_name = (EditText) dialogView.findViewById(R.id.real_name);
        user_age = (EditText) dialogView.findViewById(R.id.user_age);
        sex = (RadioGroup) dialogView.findViewById(R.id.sex);
        confirm = (Button) dialogView.findViewById(R.id.confirm);
        cancel = (Button) dialogView.findViewById(R.id.cancel);

        nick_name.setText(user.getNickName());
        real_name.setText(user.getRealName());
        user_age.setText(user.getAge());
        sexType = user.getSex();
        if ("0".equals(sexType)) {
            sex.check(R.id.female);
        } else {
            sex.check(R.id.male);
            sexType = "1";
        }

        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male:
                        sexType = "1";
                        break;
                    case R.id.female:
                        sexType = "0";
                        break;
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.updateUserInfo(nick_name.getText().toString(),
                        real_name.getText().toString(),
                        user_age.getText().toString(),
                        sexType);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setContentView(dialogView);
    }

}
