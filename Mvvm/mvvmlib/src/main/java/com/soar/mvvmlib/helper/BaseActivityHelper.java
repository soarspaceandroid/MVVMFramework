package com.soar.mvvmlib.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.soar.mvvmlib.R;
import com.soar.mvvmlib.interfaces.BaseActivityInter;
import com.soar.mvvmlib.tools.StatusBarCompat;
import com.soar.mvvmlib.views.AppBar;

/**
 * Created by gaofei on 2016/11/23.
 */
public class BaseActivityHelper implements BaseActivityInter {


    @Override
    public void onCreate(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //状态栏 底部导航栏
        if (AppBar.isConfigStatusbar()) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View
                    .SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void controlAppBar(final Activity activity , AppBar appBar) {
        setAppBarHeight(activity , appBar);
        if (AppBar.isConfigStatusbar()) {
            //statusbar height
            appBar.setStatusbarVisible(true);
            appBar.setStatusBarColor(activity.getResources().getColor(R.color.status_bar_color));
        } else {
            appBar.setStatusbarVisible(false);
        }
        appBar.setAppbarColor(activity.getResources().getColor(R.color.action_bar_color));
        appBar.getLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }


    /**
     * set app bar height
     */
    private void setAppBarHeight(Activity activity ,AppBar appBar){
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        ViewGroup.LayoutParams params = appBar.getLayoutParams();
        params.height  = StatusBarCompat.getStatusBarHeight(activity) + actionBarHeight;
        appBar.setLayoutParams(params);
    }
}
