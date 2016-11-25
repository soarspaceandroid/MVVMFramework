package com.soar.mvvmlib.net.interceptor;


import com.soar.mvvmlib.net.HttpInfo;

/**
 * 请求结果拦截器
 * @author zhousf
 */
public interface ResultInterceptor {

    HttpInfo intercept(HttpInfo info) throws Exception;

}
