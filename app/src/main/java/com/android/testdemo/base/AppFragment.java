package com.android.testdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.testdemo.utils.DoubleClickUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Admin on 2017/6/24.
 */
public abstract class AppFragment extends Fragment implements IActivity {

    protected View mLayoutView;
    protected Context mContext;
    private Unbinder mUnbinder;
    protected boolean isFirstTimeLoad=true;
    protected boolean mFragmentCreated;

    protected OnFragmentListener mOnFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setOnFragmentListener(OnFragmentListener onFragmentListener){
        this.mOnFragmentListener=onFragmentListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutView = inflater.inflate(getContentViewId(), container, false);
        mUnbinder= ButterKnife.bind(this,mLayoutView);//绑定framgent
        onCreateFragmentView(inflater, container, savedInstanceState);
        return mLayoutView;
    }

    protected void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initData();
        setListener();
        mFragmentCreated=true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()&&mFragmentCreated){//fragment界面可见时调用
            if(isFirstTimeLoad) {//第一次界面可见
                onVisible(true);
                isFirstTimeLoad=false;
            }else{//非第一次界面可见
                onVisible(false);
            }
        }else{//fragment界面不可见时调用
            onInvisible();
        }
    }

    @Override
    public void onClick(View v) {
        DoubleClickUtil.shakeClick(v);
    }

    @Override
    public void onDestroy() {
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    protected abstract void onVisible(boolean isFirstTimeLoad);
    protected abstract void onInvisible();



    public interface OnFragmentListener{
        void onFragment(String clsNameDetail, Object obj);
    }

}

