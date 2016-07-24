package com.reducepressure.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reducepressure.R;
import com.reducepressure.contract.MainContract;


/***
 * Created by Lawson on 2016/7/20.
 */
public class MyFeedBackDialog extends Dialog {

    private View dialogView;
    private EditText feedbackContent;
    private Button confirm;
    private Button cancel;
    private TextView wordsLeft;

    private int maxNumber = 100;
    private MainContract.MainPresenter mainPresenter;

    public MyFeedBackDialog(Context context, MainContract.MainPresenter mainPresenter) {
        super(context, R.style.emptyDialog);
        this.mainPresenter = mainPresenter;
        dialogView = View.inflate(context, R.layout.dialog_feedback_layout, null);
        feedbackContent = (EditText) dialogView.findViewById(R.id.user_feedback);
        confirm = (Button) dialogView.findViewById(R.id.confirm);
        cancel = (Button) dialogView.findViewById(R.id.cancel);
        wordsLeft = (TextView) dialogView.findViewById(R.id.words_left);
        setContentView(dialogView);

        initFunction();
    }

    private void initFunction() {
        wordsLeft.setText(String.valueOf(maxNumber));
        feedbackContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int current = s.length();
                int left = maxNumber - current;
                wordsLeft.setText(String.valueOf(left));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.userFeedback(feedbackContent.getText().toString());
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
