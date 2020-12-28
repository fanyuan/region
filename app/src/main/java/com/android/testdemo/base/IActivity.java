package com.android.testdemo.base;

import android.view.View;

/**
 * Title:AppActivity与AppFragment的公用接口
 * Description:
 * <p>
 * Created by pei
 * Date: 2018/3/15
 */
public interface IActivity extends View.OnClickListener{

    /**设置布局**/
    int getContentViewId();

    /**界面数据初始化**/
    void initData();

    /**界面UI事件监听**/
    void setListener();


}
