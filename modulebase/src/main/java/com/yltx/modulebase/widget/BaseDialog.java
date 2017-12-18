package com.yltx.modulebase.widget;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

public class BaseDialog extends AbsDialog {
    private ViewConvertListener convertListener;

    public static BaseDialog init() {
        return new BaseDialog();
    }

    @Override
    public int intLayoutId() {
        return layoutId;
    }

    @Override
    public void convertView(ViewHolder holder, AbsDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }


    public BaseDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public BaseDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            convertListener = (ViewConvertListener) savedInstanceState.getSerializable("convertListener");
        }
    }

    /**
     * 保存接口
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("convertListener", convertListener);
    }

}