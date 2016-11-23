package com.soar.mvvm;

import android.os.Bundle;

import com.soar.mvvmlib.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public String getCurrentTitle() {
        return "test";
    }
}
