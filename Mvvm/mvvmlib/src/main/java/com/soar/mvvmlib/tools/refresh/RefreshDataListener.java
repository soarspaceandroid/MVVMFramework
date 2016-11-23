package com.soar.mvvmlib.tools.refresh;

/**
 * Created by gaofei on 2016/6/13.
 */
public interface RefreshDataListener {

    public final static int HOME_LOCATION = 0 ; // home 页面刷新
    public final static int USRECENTER_LOCATION = 1 ; // usercenter 页面刷新

    /**
     * 刷新数据
     */
    public void refreshAllData();

    /**
     * register
     * @param refreshDataImp
     */
    public void register(int position, RefreshDataImp refreshDataImp);

    /**
     * refresh data by position
     * @param position
     */
    public void refreshDataByPosition(int position);

    /**
     * unregister
     * @param position
     */
    public void unRegister(int position);
}
