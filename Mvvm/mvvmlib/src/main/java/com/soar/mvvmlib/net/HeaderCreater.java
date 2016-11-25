package com.soar.mvvmlib.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaofei on 2016/11/25.
 */
public class HeaderCreater {


    private Map<String , String>  headers = new HashMap<>();

    public HeaderCreater addHeader(String key , String value){
        headers.put(key , value);
        return this;
    }


    public Map<String , String> build(){
        return headers;
    }

}
