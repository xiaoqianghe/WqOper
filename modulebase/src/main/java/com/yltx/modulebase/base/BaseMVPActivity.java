package com.yltx.modulebase.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yltx.modulebase.R;
import com.yltx.modulebase.net.RxManager;



/**
 * 功能描述:
 * Created by ixzus on 2017/8/3.
 */

public abstract class BaseMVPActivity<P extends BasePresenter> extends RxAppCompatActivity implements BaseContract.IBaseView {
    protected P presenter;
    private ImmersionBar mImmersionBar;
    protected String TAG;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getPackageName() + "." + getClass().getSimpleName();
        presenter = initPresenter();
        if (null != presenter) {
            presenter.attatch(initModule(), this);
        }
        setContentView(initLayout());
        initView();
        initData();
        mImmersionBar = ImmersionBar.with(this);
//        if (null != mImmersionBar) {
////            if (null != findViewById(R.id.toolbar_base)) {
////                mImmersionBar.titleBar(findViewById(R.id.toolbar_base));
////            }
//            mImmersionBar.fitsSystemWindows(true);  //使用该属性,必须指定状态栏颜色
//            mImmersionBar.statusBarColor(R.color.white);
//            mImmersionBar.init();
//        }

        ActivityManager.getInstance().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxManager.getInstance().clear(TAG);
        if (null != presenter) {
            presenter.detach();
        }
        if (null != mImmersionBar) {
            mImmersionBar.destroy();
        }
        ActivityManager.getInstance().removeActivity(this);
    }

    protected abstract P initPresenter();

    protected abstract BaseModel initModule();

    protected abstract
    @LayoutRes
    int initLayout();

    protected abstract void initView();

    protected abstract void initData();
    private Toolbar getToolBar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    private TextView getToolBarTitle() {
        return (TextView) findViewById(R.id.toolbar_title);
    }

    private TextView getToolBarSubTitle() {
        return (TextView) findViewById(R.id.toolbar_subtitle);
    }

    protected void initToolBar(boolean isBack, String title, String subTitle) {
        if (null != getToolBar()) {
            if (isBack) {
                getToolBar().setNavigationIcon(R.mipmap.base_ic_back);
                getToolBar().setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            }
            if (!TextUtils.isEmpty(title) && null != getToolBarTitle()) {
                getToolBarTitle().setText(title);
            }
            if (!TextUtils.isEmpty(subTitle) && null != getToolBarSubTitle()) {
                getToolBarSubTitle().setText(subTitle);
            }
        }
    }
}

