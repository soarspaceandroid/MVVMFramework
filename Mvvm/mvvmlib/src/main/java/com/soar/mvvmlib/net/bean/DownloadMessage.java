package com.soar.mvvmlib.net.bean;


import com.soar.mvvmlib.net.HttpInfo;
import com.soar.mvvmlib.net.callback.ProgressCallback;

/**
 * 下载响应回调信息体
 * @author zhousf
 */
public class DownloadMessage extends OkMessage{

    public String filePath;
    public HttpInfo info;
    public ProgressCallback progressCallback;

    public DownloadMessage(int what, String filePath, HttpInfo info, ProgressCallback progressCallback) {
        this.what = what;
        this.filePath = filePath;
        this.info = info;
        this.progressCallback = progressCallback;
    }
}