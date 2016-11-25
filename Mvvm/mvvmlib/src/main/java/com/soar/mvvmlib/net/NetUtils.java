package com.soar.mvvmlib.net;

import com.soar.mvvmlib.base.BaseApplication;
import com.soar.mvvmlib.net.annotation.CacheLevel;
import com.soar.mvvmlib.net.bean.BaseBean;
import com.soar.mvvmlib.net.callback.CallbackOk;

import java.util.Map;

/**
 * Created by gaofei on 2016/11/25.
 */
public class NetUtils {


    private static  NetUtils netUtils;
    private OkHttpUtil.Builder okHttpBuilder;
    private HttpInfo.Builder infoBuilder;
    private CallbackOk callbackOk;
    private Config config;

    public static NetUtils getInstance(){
        synchronized (NetUtils.class){
            if(netUtils == null){
                synchronized (NetUtils.class){
                    netUtils = new NetUtils();
                }
            }
        }
        return netUtils;
    }

    /**
     * init
     */
    public NetUtils() {
        config = BaseApplication.getInstance().getConfig();
        okHttpBuilder = OkHttpUtil.Builder()
                .setCacheLevel(CacheLevel.FIRST_LEVEL)
                .setConnectTimeout(config.configConnecttimeout());
        infoBuilder = HttpInfo.Builder();

    }


    /**
     * set header
     * @param headers
     * @return
     */
    public NetUtils setHeader(Map<String , String>  headers){
        infoBuilder.addHeads(headers);
        return this;
    }


    /**
     * set params
     * @param params
     * @return
     */
    public NetUtils setParams(Map<String , String>  params){
        infoBuilder.addParams(params);
        return this;
    }

    /**
     * set result listener
     * @param callbackOk
     * @return
     */
    public NetUtils setResponseListener(CallbackOk callbackOk){
        this.callbackOk = callbackOk;
        return this;
    }

    /**
     * set reqeust api
     * @param api
     * @return
     */
    public NetUtils setRequestApi(String api){
        infoBuilder.setUrl(config.getBaseUrl()+api);
        return this;
    }


    public NetUtils setTclass(Class<? extends BaseBean> tclass){
        infoBuilder.setClass(tclass);
        return this;
    }

    /**
     *  build and to request data
     */
    public void build(){
        okHttpBuilder.build().doPostAsync(infoBuilder.build() , callbackOk);
    }

}
