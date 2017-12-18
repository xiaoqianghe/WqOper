package com.yltx.modulebase.base;

/**
 * 功能描述:
 * Created by ixzus on 2017/8/3.
 */

public class BasePresenter<M extends BaseModel, V extends BaseContract.IBaseView> {
    protected M model;
    protected V view;

    void attatch(M m, V v) {
        this.model = m;
        this.view = v;
    }

    void detach() {
        this.model = null;
        this.view = null;
    }
}
