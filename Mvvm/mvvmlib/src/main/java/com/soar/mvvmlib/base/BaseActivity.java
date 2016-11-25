package com.soar.mvvmlib.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.soar.mvvmlib.R;
import com.soar.mvvmlib.helper.BaseActivityHelper;
import com.soar.mvvmlib.interfaces.BaseActivityInter;
import com.soar.mvvmlib.net.HttpInfo;
import com.soar.mvvmlib.net.NetUtils;
import com.soar.mvvmlib.net.bean.BaseBean;
import com.soar.mvvmlib.net.callback.CallbackOk;
import com.soar.mvvmlib.views.AppBar;
import com.soar.mvvmlib.views.ErrorViewLayout;

import java.io.IOException;

/**
 * Created by gaofei on 2016/11/14.
 */
public abstract class BaseActivity extends AppCompatActivity implements CallbackOk {

    private AppBar appBar;
    private LayoutInflater layoutInflater;
    private ErrorViewLayout errorViewLayout;
    private BaseActivityInter helper;
    public NetUtils netUtils;
    private Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        helper = new BaseActivityHelper();
        helper.onCreate(this);
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        initBaseView();
        initBaseData();
    }

    private void initBaseView(){
        layoutInflater = LayoutInflater.from(this);
        appBar = (AppBar)findViewById(R.id.app_bar);
        errorViewLayout = (ErrorViewLayout)findViewById(R.id.root_container);
        controlAppBar(appBar);
    }


    private void initBaseData(){
        gson = new Gson();
        netUtils = NetUtils.getInstance();
        netUtils.setResponseListener(this);
        requestData();
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

    @Override
    public void onResponse(HttpInfo info) throws IOException {
        Log.e("soar" , "base0  --- "+info.isSuccessful()+"   "+info.getTclass());
        if(info.isSuccessful()){
            BaseBean baseBean = gson.fromJson(info.getRetDetail() , info.getTclass());
            Log.e("soar" , "base 11 --- "+baseBean.getClass());
            updateUI(baseBean);
        }else{
            errorUI(info.getRetCode());
        }
    }

    public void updateUI(BaseBean baseBean){
        Log.e("soar" , "base1  --- updataUI");
    }

    public void errorUI(int code){
        Log.e("soar" , "base2  --- "+code);
    }
}
