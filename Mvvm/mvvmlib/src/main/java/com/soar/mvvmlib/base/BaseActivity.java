package com.soar.mvvmlib.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.soar.mvvmlib.R;
import com.soar.mvvmlib.helper.BaseActivityHelper;
import com.soar.mvvmlib.interfaces.BaseActivityInter;
import com.soar.mvvmlib.views.AppBar;
import com.soar.mvvmlib.views.ErrorViewLayout;

/**
 * Created by gaofei on 2016/11/14.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private AppBar appBar;
    private LayoutInflater layoutInflater;
    private ErrorViewLayout errorViewLayout;
    private BaseActivityInter helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        helper = new BaseActivityHelper();
        helper.onCreate(this);
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        initBaseView();
    }

    private void initBaseView(){
        layoutInflater = LayoutInflater.from(this);
        appBar = (AppBar)findViewById(R.id.app_bar);
        errorViewLayout = (ErrorViewLayout)findViewById(R.id.root_container);
        controlAppBar(appBar);
    }


    /**
     *  此处只重写一个setcontentview  使用layoutId的这个 View 和 view params的暂时忽略
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        layoutInflater.inflate(layoutResID , errorViewLayout);
    }


    /**
     *  control appbar ,  如果需要修改appbar 请重写该方法
     */
    public void controlAppBar(AppBar appBar){
        helper.controlAppBar(this , appBar);
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
