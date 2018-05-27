package com.example.asus.wook3.net;

import com.example.asus.wook3.bean.SatinBean;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asus on 2018/5/26.
 */

public interface SatinApiService {
    @FormUrlEncoded
    @POST("satinApi")
    Observable<SatinBean> getSatinApi(@Field("type") String type,
                                      @Field("page") String page);
}
