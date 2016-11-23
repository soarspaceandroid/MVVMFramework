package com.soar.mvvmlib.tools.request;

/**
 * Created by gaofei on 2016/9/26.
 */
public interface RequestListener<T> {

    /**
     * 响应成功
     */
    void onReqSuccess(T result);

    /**
     * 响应失败
     */
    void onReqFailed(String errorMsg);

}
