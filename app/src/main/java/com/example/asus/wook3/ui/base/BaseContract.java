package com.example.asus.wook3.ui.base;

/**
 * Created by asus on 2018/5/26.
 */

public interface BaseContract {
    interface BaseView {
        void showloading();
        void dismissloading();
    }
    interface BasePresenter<T extends BaseView> {
        void attchview(T view);
        void detachview();
    }
}
