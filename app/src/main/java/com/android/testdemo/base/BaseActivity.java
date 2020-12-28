package com.android.testdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.android.testdemo.utils.DoubleClickUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Title:
 * Description:
 * <p>
 * Created by pei
 * Date: 2018/2/9
 */
public abstract class BaseActivity extends AppCompatActivity implements IActivity{

    protected View mLayoutView;//总布局
    protected Activity mContext;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mContext = this;

        if (getContentViewId() != 0) {
            mLayoutView = LayoutInflater.from(mContext).inflate(getContentViewId(), null);
            setContentView(mLayoutView);

            mUnbinder = ButterKnife.bind(this);
        }

        initData();
        setListener();
    }

    @Override
    public void onClick(View v) {
        DoubleClickUtil.shakeClick(v);
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }



}
