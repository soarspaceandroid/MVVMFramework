package com.soar.mvvmlib.tools.request;

import android.os.Build;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gaofei on 2016/9/26.
 * 此类主要给第三方请求 使用
 */
public class ReqeustUtils {


    /**
     * get request
     * @param url
     * @param paramsMap
     * @param requestListener
     * @param tClass
     * @param <T>
     * @return
     */
    private <T> Call requestGet(String url, HashMap<String, String> paramsMap, final RequestListener<T> requestListener , final Class<T> tClass) {
        StringBuilder tempParams = new StringBuilder();
        try {
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String requestUrl = String.format("%s?%s", url, tempParams.toString());
            final Request request = addHeaders().url(requestUrl).build();
            final Call call = new OkHttpClient().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    requestListener.onReqFailed(e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        T t = new Gson().fromJson(json , tClass);
                        requestListener.onReqSuccess(t);
                    } else {
                        requestListener.onReqFailed("服务器错误");
                    }
                }
            });
            return call;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * okHttp post异步请求
     * @param url 接口地址
     * @param paramsMap 请求参数
     * @param requestListener 请求返回数据回调
     * @param <T> 数据泛型
     * @return
     */
    private <T> Call requestPost(String url, HashMap<String, String> paramsMap, final RequestListener<T> requestListener , final Class<T> tClass) {
        try {
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String params = tempParams.toString();
            RequestBody body = RequestBody.create(MediaType.parse(params), params);
            String requestUrl = String.format("%s", url);
            final Request request = addHeaders().url(requestUrl).post(body).build();
            final Call call = new OkHttpClient().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    requestListener.onReqFailed(e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        T t = new Gson().fromJson(json , tClass);
                        requestListener.onReqSuccess(t);
                    } else {
                        requestListener.onReqFailed("服务器错误");
                    }
                }
            });
            return call;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 统一为请求添加头信息
     * @return
     */
    private Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder()
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", "3.2.0");
        return builder;
    }


}
