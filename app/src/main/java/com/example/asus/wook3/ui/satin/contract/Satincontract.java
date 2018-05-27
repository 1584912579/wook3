package com.example.asus.wook3.ui.satin.contract;

import com.example.asus.wook3.bean.SatinBean;
import com.example.asus.wook3.ui.base.BaseContract;

import java.util.List;

/**
 * Created by asus on 2018/5/26.
 */

public interface Satincontract {
    interface View extends BaseContract.BaseView{
        void getSatinSuccess(List<SatinBean.DataBean> list);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getPresenter(String type,String page);
    }

}
