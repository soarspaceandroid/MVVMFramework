package com.soar.mvvm;

import com.soar.mvvmlib.base.BaseApplication;
import com.soar.mvvmlib.net.Config;

/**
 * Created by gaofei on 2016/11/25.
 */
public class MyApplication extends BaseApplication {


    @Override
    public Config getConfig() {
        return new NetConfig();
    }
}
