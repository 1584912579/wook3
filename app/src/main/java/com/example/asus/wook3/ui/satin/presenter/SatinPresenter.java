package com.example.asus.wook3.ui.satin.presenter;

import com.example.asus.wook3.bean.SatinBean;
import com.example.asus.wook3.net.SatinApi;
import com.example.asus.wook3.ui.base.BaseContract;
import com.example.asus.wook3.ui.base.BasePresenter;
import com.example.asus.wook3.ui.satin.contract.Satincontract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/26.
 */

public class SatinPresenter extends BasePresenter<Satincontract.View> implements Satincontract.Presenter  {
    private SatinApi satinApi;
    @Inject
    public SatinPresenter(SatinApi satinApi) {
        this.satinApi = satinApi;
    }

    @Override
    public void getPresenter(String type, String page) {
        satinApi.getSatinApi(type,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<SatinBean, List<SatinBean.DataBean>>() {
                    @Override
                    public List<SatinBean.DataBean> apply(SatinBean satinBean) throws Exception {
                        return satinBean.getData();
                    }
                }).subscribe(new Consumer<List<SatinBean.DataBean>>() {
            @Override
            public void accept(List<SatinBean.DataBean> dataBeans) throws Exception {
                if (mView!=null){
                    mView.getSatinSuccess(dataBeans);
                }
            }
        });
    }
}
