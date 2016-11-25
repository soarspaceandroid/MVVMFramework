package com.soar.mvvmlib.net;

/**
 * Created by gaofei on 2016/11/25.
 */
public abstract class Config {


    private static Config instanse;

    public static Config getInstance(){
        synchronized (Config.class){
            if(instanse == null){
                instanse = new Config() {
                    @Override
                    public int configConnecttimeout() {
                        return 0;
                    }

                    @Override
                    public String getBaseUrl() {
                        return null;
                    }
                };
            }
        }
        return instanse;
    }


    public abstract int configConnecttimeout();


    public abstract String getBaseUrl();

}
