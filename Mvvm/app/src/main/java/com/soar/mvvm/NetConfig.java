package com.soar.mvvm;

import com.soar.mvvmlib.net.Config;

/**
 * Created by gaofei on 2016/11/25.
 */
public class NetConfig extends Config{

    public final int CONNECTION_TIME_OUT = 25; //设置超时

    public final  String BASE_URL = "http://www.tngou.net/api/";

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @Override
    public int configConnecttimeout() {
        return CONNECTION_TIME_OUT;
    }

    public static class Api{
        public final static String LIST_API = "lore/list";
    }

}
