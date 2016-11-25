package com.soar.mvvmlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soar.mvvmlib.R;
import com.soar.mvvmlib.interfaces.BaseActivityInter;
import com.soar.mvvmlib.views.AppBar;
import com.soar.mvvmlib.views.ErrorViewLayout;

/**
 * Created by gaofei on 2016/11/14.
 */
public abstract class BaseFragment extends Fragment {

    private AppBar appBar;
    private LayoutInflater layoutInflater;
    private ErrorViewLayout errorViewLayout;
    private BaseActivityInter helper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater = inflater;
        View view = layoutInflater.inflate(R.layout.activity_base , null);
        initBaseView(view);
        errorViewLayout.addView(onCreateView(inflater , errorViewLayout , savedInstanceState));
        initBaseData();
        return view;
    }



    private void initBaseView(View view){
        appBar = (AppBar)view.findViewById(R.id.app_bar);
        errorViewLayout = (ErrorViewLayout)view.findViewById(R.id.root_container);
        controlAppBar(appBar);
    }


    private void initBaseData(){
        requestData();
    }



    /**
     *  control appbar ,  如果需要修改appbar 请重写该方法
     */
    public void controlAppBar(AppBar appBar){
        helper.controlAppBar(getActivity() , appBar);
        appBar.setTitle(getCurrentTitle());
        appBar.setLeftImage(R.drawable.back);
    }


    /**
     *  activity title
     * @return
     */
    public abstract  String getCurrentTitle();


    public void requestData(){

    }


}
