package com.yltx.modulebase.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 功能描述:
 * Created by ixzus on 2017/8/18.
 */

public abstract class BaseMVPFragment<P extends BasePresenter> extends LazyFragment implements BaseContract.IBaseView {
    protected P presenter;
    protected String TAG;
    protected Activity mContext;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        if (0 != initLayout()) {
            rootView = inflater.inflate(initLayout(), container, false);
            initView();
            return rootView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TAG = getActivity().getPackageName() + "." + getClass().getSimpleName();
        presenter = initPresenter();
        if (null != presenter) {
            presenter.attatch(initModule(), this);
        }
        initView();
        initData();

    }


    protected abstract P initPresenter();

    protected abstract BaseModel initModule();

    protected abstract
    @LayoutRes
    int initLayout();

    protected abstract void initView();

    protected abstract void initData();

}
