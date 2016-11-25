package com.soar.mvvmlib.net.callback;


import com.soar.mvvmlib.net.HttpInfo;

import java.io.IOException;

/**
 * 异步请求回调接口
 * @author zhousf
 */
public interface CallbackOk {
    /**
     * 该回调方法已切换到UI线程
     */
    void onResponse(HttpInfo info) throws IOException;
}
