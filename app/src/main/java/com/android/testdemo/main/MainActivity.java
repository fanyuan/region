package com.android.testdemo.main;

import android.view.View;

import com.android.testdemo.R;
import com.android.testdemo.base.BaseActivity;
import com.android.testdemo.utils.ToastUtil;

public class MainActivity extends BaseActivity{

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    private void showShort(String msg){
        ToastUtil.shortShow(mContext,msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}