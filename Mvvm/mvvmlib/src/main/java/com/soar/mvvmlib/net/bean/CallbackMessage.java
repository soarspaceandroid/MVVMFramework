package com.soar.mvvmlib.net.bean;


import com.soar.mvvmlib.net.HttpInfo;
import com.soar.mvvmlib.net.callback.CallbackOk;

/**
 * 响应回调信息体
 * @author zhousf
 */
public class CallbackMessage extends OkMessage{

    public CallbackOk callback;
    public HttpInfo info;

    public CallbackMessage(int what, CallbackOk callback, HttpInfo info) {
        this.what = what;
        this.callback = callback;
        this.info = info;
    }


}