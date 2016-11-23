package com.soar.mvvmlib.tools.ui;

import android.content.Context;
import android.widget.Toast;

import com.soar.mvvmlib.tools.refresh.RefreshDataListener;
import com.soar.mvvmlib.tools.refresh.RefreshHelper;


/**
 * Created by gaofei on 2016/9/21.
 */
public class UiShowper {


    /**
     * 统一toast显示
     * @param context
     * @param text
     * @param time
     */
    public static void showToast(Context context , String text , int time){
//        final Toast toast = new Toast(context);
//        ToastView toastView = new ToastView(context);
//        toastView.setToastText(text);
//        toast.setView(toastView);
//        toast.setText(text);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.BOTTOM, 0 , 50);
//        toast.show();
        Toast.makeText(context , text , Toast.LENGTH_LONG).show();
    }


    /**
     * show toast
     * @param context
     * @param text
     */
    public static void showToast(Context context , String text){
        showToast(context , text , 3000);
    }

    /**
     * show toast
     * @param context
     * @param stringRes
     */
    public static void showToast(Context context , int stringRes){
        showToast(context , context.getString(stringRes) , 3000);
    }



    /**
     * 首页刷新help
     */
    private static RefreshDataListener refreshImp;

    public static RefreshDataListener getFreshInstance() {
        if (refreshImp == null) {
            refreshImp = new RefreshHelper();
        }
        return refreshImp;
    }

}
