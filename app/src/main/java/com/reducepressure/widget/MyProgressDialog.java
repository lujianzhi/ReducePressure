package com.reducepressure.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.reducepressure.R;


/***
 * Created by Lawson on 2016/7/20.
 */
public class MyProgressDialog extends Dialog {

    private View popView;
    private ImageView loadingView;

    public MyProgressDialog(Context context) {
        super(context, R.style.emptyDialog);
        popView = View.inflate(context, R.layout.progress_dialog_layout, null);
        loadingView = (ImageView) popView.findViewById(R.id.loading);
        loadingView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.loading_rotate));
        setContentView(popView);
    }

}
