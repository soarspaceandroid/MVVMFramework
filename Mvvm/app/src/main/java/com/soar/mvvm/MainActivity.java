package com.soar.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.soar.mvvm.databinding.ActivityMainBinding;
import com.soar.mvvmlib.base.BaseActivity;
import com.soar.mvvmlib.net.ParamsCreater;
import com.soar.mvvmlib.net.bean.BaseBean;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding viewDataBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewDataBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);
//        ActivityBasicBinding binding = DataBindingUtil.setContentView(
//                this, R.layout.activity_basic);
//        User user = new User("fei", "Liang");
//        binding.setUser(user);
        TextView textView = (TextView)findViewById(R.id.test);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
    }

    @Override
    public String getCurrentTitle() {
        return "test";
    }

    @Override
    public void requestData() {
        super.requestData();
        netUtils.setRequestApi(NetConfig.Api.LIST_API)
                .setParams(new ParamsCreater().addParams("page" , "1").addParams("rows" , "2").addParams("id" , "1").build())
                .setTclass(TestBean.class)
                .build();
    }

    @Override
    public void updateUI(BaseBean baseBean) {
        super.updateUI(baseBean);
        Log.e("soar" , "test --- "+(baseBean instanceof  TestBean) +"   "+baseBean.getClass());
        if(baseBean instanceof TestBean){
            TestBean testBean = (TestBean)baseBean;
            viewDataBinding.setTestBean(testBean);
            Log.e("soar" , "------ "+testBean.tngou.get(0).description+"   "+testBean.tngou.get(0).title);
        }
    }

    @Override
    public void errorUI(int code) {
        super.errorUI(code);
    }
}
