package com.soar.mvvmlib.base;

import android.app.Application;

import com.soar.mvvmlib.net.Config;

/**
 * Created by gaofei on 2016/11/24.
 */
public abstract class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getInstance(){
        return instance;
    }


    public abstract Config getConfig();

}
