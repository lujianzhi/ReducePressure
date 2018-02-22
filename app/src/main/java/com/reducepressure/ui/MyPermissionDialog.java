package com.reducepressure.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reducepressure.R;
import com.reducepressure.utils.MyCommonUtils;

import butterknife.ButterKnife;

/**
 * Created by Ian.Lu on 2018/2/22.
 * Project : ReducePressure
 */

public class MyPermissionDialog extends DialogFragment implements View.OnClickListener {

    /**
     * 权限名称
     */
    public static String PERMISSION_TEXT = "PERMISSION_TEXT";

    private TextView permissionTextTv;
    private Button confirmBtn;
    private Button cancelBtn;

    private DialogButtonListener dialogButtonListener;

    public static MyPermissionDialog newInstance(MyPermissionDialog.DialogButtonListener dialogButtonListener, String permissionText) {
        MyPermissionDialog myPermissionDialog = new MyPermissionDialog();
        myPermissionDialog.setDialogButtonListener(dialogButtonListener);
        Bundle data = new Bundle();
        data.putString(MyPermissionDialog.PERMISSION_TEXT, permissionText);
        myPermissionDialog.setArguments(data);
        return myPermissionDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_permission, null);
        permissionTextTv = (TextView) view.findViewById(R.id.permission_text);
        confirmBtn = (Button) view.findViewById(R.id.confirm);
        cancelBtn = (Button) view.findViewById(R.id.cancel);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        Bundle data = getArguments();
        if (MyCommonUtils.isNotNull(data)) {
            permissionTextTv.setText(getContext().getResources().getString(R.string.permission_text, data.getString(PERMISSION_TEXT, "")));
        }
        confirmBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    public void setDialogButtonListener(DialogButtonListener dialogButtonListener) {
        this.dialogButtonListener = dialogButtonListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                if (MyCommonUtils.isNotNull(dialogButtonListener)) {
                    dialogButtonListener.onConfirm();
                }
                break;
            case R.id.cancel:
                if (MyCommonUtils.isNotNull(dialogButtonListener)) {
                    dialogButtonListener.onCancel();
                }
                break;

            default:
                break;
        }
        dismiss();
    }

    public interface DialogButtonListener {
        void onConfirm();

        void onCancel();
    }
}
