package com.reducepressure.view;

import android.content.Context;

/***
 * Created by Lawson on 2016/7/19.
 */
public interface IBaseView {

    Context getContext();

    void startLoading();

    void stopLoading();
}
