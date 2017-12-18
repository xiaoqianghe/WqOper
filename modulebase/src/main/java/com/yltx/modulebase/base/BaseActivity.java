package com.yltx.modulebase.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yltx.modulebase.R;
import com.yltx.modulebase.net.RxManager;



/**
 * 功能描述:
 * Created by ixzus on 2017/8/3.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    private ImmersionBar mImmersionBar;
    protected String TAG;
    protected Context mContext=this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getPackageName() + "." + getClass().getSimpleName();
        setContentView(initLayout());
        initView(savedInstanceState);
        initData();
        mImmersionBar = ImmersionBar.with(this);
        if (null != mImmersionBar) {
//            if (null != findViewById(R.id.toolbar_base)) {
//                mImmersionBar.titleBar(findViewById(R.id.toolbar_base));
//            }
//            mImmersionBar.statusBarAlpha(0.1f);
            mImmersionBar.fitsSystemWindows(true);  //使用该属性,必须指定状态栏颜色
            mImmersionBar.statusBarColor(R.color.colorStatus);
//            mImmersionBar.navigationBarColor(R.color.colorStatus);
            mImmersionBar.init();
        }
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxManager.getInstance().clear(TAG);
        if (null != mImmersionBar) {
            mImmersionBar.destroy();
        }
        ActivityManager.getInstance().removeActivity(this);
    }


    protected abstract
    @LayoutRes
    int initLayout();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    protected abstract void initData();

    private Toolbar getToolBar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    private TextView getToolBarTitle() {
        return (TextView) findViewById(R.id.toolbar_title);
    }
    private ImageView getToolBarRightDrawable(){
        return (ImageView) findViewById(R.id.toolbar_ic);
    }

    private TextView getToolBarSubTitle() {
        return (TextView) findViewById(R.id.toolbar_subtitle);
    }

    protected void initToolBar(boolean isBack, String title, String subTitle) {
        if (null != getToolBar()) {
            setSupportActionBar(getToolBar());
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            if (isBack) {
                getToolBar().setNavigationIcon(R.mipmap.base_ic_back);
                getToolBar().setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
            if (null != getToolBarTitle()) {
                if (TextUtils.isEmpty(title)) {
                    getToolBarTitle().setText("");
                } else {
                    getToolBarTitle().setText(title);
                }
            }
            if (null != getToolBarSubTitle()) {
                if (TextUtils.isEmpty(subTitle)) {
                    getToolBarSubTitle().setText("");
                } else {
                    getToolBarSubTitle().setText(subTitle);
                }
            }
        }
    }
    protected void initToolBar(boolean isBack, String title, String subTitle,boolean isIc) {
        if (null != getToolBar()) {
            setSupportActionBar(getToolBar());
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            if (isBack) {
                getToolBar().setNavigationIcon(R.mipmap.base_ic_back);
                getToolBar().setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
            if (null != getToolBarTitle()) {
                if (TextUtils.isEmpty(title)) {
                    getToolBarTitle().setText("");
                } else {
                    getToolBarTitle().setText(title);
                }
            }
            if (null != getToolBarSubTitle()) {
                if (TextUtils.isEmpty(subTitle)) {
                    getToolBarSubTitle().setText("");
                } else {
                    getToolBarSubTitle().setText(subTitle);
                }
            }
            if(isIc){
                getToolBarRightDrawable().setVisibility(View.VISIBLE);
            }else{
                getToolBarRightDrawable().setVisibility(View.GONE);
            }
        }
    }

}

