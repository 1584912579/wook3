package com.example.asus.wook3.net;

import com.example.asus.wook3.bean.SatinBean;

import io.reactivex.Observable;

/**
 * Created by asus on 2018/5/26.
 */

public class SatinApi {
    private static SatinApi satinApi;
    private SatinApiService satinApiService;

    private SatinApi(SatinApiService satinApiService) {
        this.satinApiService = satinApiService;
    }

    public static SatinApi getSatinApi(SatinApiService satinApiService) {
        if (satinApi == null) {
            satinApi = new SatinApi(satinApiService);
        }
        return satinApi;
    }

    public Observable<SatinBean> getSatinApi(String type, String page) {
        return satinApiService.getSatinApi(type,page);
    }
}
