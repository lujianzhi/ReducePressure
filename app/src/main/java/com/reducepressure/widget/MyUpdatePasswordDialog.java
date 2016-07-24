package com.reducepressure.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.reducepressure.R;
import com.reducepressure.contract.MainContract;

/***
 * Created by Lawson on 2016/7/24.
 */
public class MyUpdatePasswordDialog extends Dialog {

    private MainContract.MainPresenter mainPresenter;

    private View dialogView;
    private EditText oldPassword;
    private EditText newPassword;
    private EditText newPasswordAgain;
    private Button confirm;
    private Button cancel;

    public MyUpdatePasswordDialog(Context context, MainContract.MainPresenter mainPresenter) {
        super(context, R.style.emptyDialog);
        this.mainPresenter = mainPresenter;
        dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_update_password_layout, null, false);
        oldPassword = (EditText) dialogView.findViewById(R.id.old_password);
        newPassword = (EditText) dialogView.findViewById(R.id.new_password);
        newPasswordAgain = (EditText) dialogView.findViewById(R.id.new_password_again);
        confirm = (Button) dialogView.findViewById(R.id.confirm);
        cancel = (Button) dialogView.findViewById(R.id.cancel);
        setContentView(dialogView);

        initFunction();
    }

    private void initFunction() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.updatePassword(oldPassword.getText().toString(), newPassword.getText().toString(), newPasswordAgain.getText().toString());
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
