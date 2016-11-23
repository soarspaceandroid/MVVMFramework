package com.soar.mvvmlib.interfaces;

import android.app.Activity;

import com.soar.mvvmlib.views.AppBar;

/**
 * Created by gaofei on 2016/11/23.
 */
public interface BaseActivityInter {

    void onCreate(Activity activity);


    void controlAppBar(Activity activity ,AppBar appBar);
}
