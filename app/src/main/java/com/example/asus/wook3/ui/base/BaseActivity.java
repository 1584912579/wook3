package com.example.asus.wook3.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.asus.wook3.inter.IBase;

import javax.inject.Inject;

/**
 * Created by asus on 2018/5/26.
 */

public abstract class BaseActivity<T extends BaseContract.BasePresenter>  extends AppCompatActivity implements IBase,BaseContract.BaseView {
    @Inject
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        inject();
        if (mPresenter!=null){
            mPresenter.attchview(this);
        }
    }

    @Override
    public void initView(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachview();
        }
    }

    @Override
    public void showloading() {

    }

    @Override
    public void dismissloading() {

    }
}
