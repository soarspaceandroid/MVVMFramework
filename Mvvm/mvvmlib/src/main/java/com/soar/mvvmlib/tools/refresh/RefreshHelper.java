package com.soar.mvvmlib.tools.refresh;

/**
 * Created by gaofei on 2016/9/30.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaofei on 2016/6/13.
 */
public class RefreshHelper implements RefreshDataListener{

    /**
     * 此listview 默认10个界面刷新 , 初始化10 , 默认10个界面同时刷新, 所以这个数字有限. 请使用时候在ondestory中unregister  ,
     * register 和 unregister 在YLBbaseActivity 和YLBBaseFragment中都有对应的方法直接调用
     */
    private static List<RefreshDataImp> refreshDataImpList = new ArrayList<RefreshDataImp>(){
        {
            add(0 , null);
            add(1 , null);
            add(2 , null);
            add(3 , null);
            add(4 , null);
            add(5 , null);
            add(6 , null);
            add(7 , null);
            add(8 , null);
            add(9 , null);
        }
    };

    /**
     * 刷新
     */
    @Override
    public void refreshAllData() {
        for(RefreshDataImp imp : refreshDataImpList){
            if(imp == null){
                continue;
            }
            imp.onRefreshData();
        }
    }

    /**
     * register
     * @param refreshDataImp
     */
    @Override
    public void register(int position , RefreshDataImp refreshDataImp){
        try {
            if(position > 9){
                throw new Exception("默认只有10界面刷新, 请定义position小于10的数字 , Thanks");
            }
            if(refreshDataImpList.get(position) != null){
                throw new Exception("这个position的值 , 已经被占用,请使用其他值, 注意,最多只能10个");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ;
        }
        refreshDataImpList.remove(position);
        refreshDataImpList.add(position , refreshDataImp);
    }

    /**
     * unregister
     * @param position
     */
    public void unRegister(int position){
        refreshDataImpList.remove(position);
        refreshDataImpList.add(position , null);
    }

    /**
     * refresh data by posituion
     * @param position
     */
    @Override
    public void refreshDataByPosition(int position) {
        if(refreshDataImpList.size() == 0 || position > refreshDataImpList.size()){
            return;
        }
        if(refreshDataImpList.get(position) == null){
            return;
        }
        refreshDataImpList.get(position).onRefreshData();
    }
}
