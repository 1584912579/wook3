package com.example.asus.wook3.ui.base;

/**
 * Created by asus on 2018/5/26.
 */

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    protected T mView;
    @Override
    public void attchview(T view) {
        this.mView=view;
    }

    @Override
    public void detachview() {
        if (mView!=null){
            mView=null;
        }
    }
}
