package com.soar.mvvmlib.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaofei on 2016/11/25.
 */
public class ParamsCreater {


    private Map<String , String>  params = new HashMap<>();

    public ParamsCreater addParams(String key , String value){
        params.put(key , value);
        return this;
    }

    public Map<String , String> build(){
        return params;
    }


}
