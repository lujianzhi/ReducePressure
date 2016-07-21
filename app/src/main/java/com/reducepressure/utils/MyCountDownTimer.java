package com.reducepressure.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

/***
 * Created by Lawson on 2016/7/20.
 */
public class MyCountDownTimer extends CountDownTimer {

    private Button countDownButton;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountDownTimer(Button countDownButton, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.countDownButton = countDownButton;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        countDownButton.setEnabled(false);
        String timeLeft = String.valueOf(millisUntilFinished / 1000) + "秒后重新发送";
        countDownButton.setText(timeLeft);

        //将倒计时的时间设置为红色
        SpannableString str = new SpannableString(countDownButton.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        str.setSpan(span, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        countDownButton.setText(str.toString());
    }

    @Override
    public void onFinish() {
        countDownButton.setEnabled(true);
        countDownButton.setText("重新获取验证码");
    }
}
