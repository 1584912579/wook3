package com.example.asus.wook3.module;

import com.example.asus.wook3.net.SatinApi;
import com.example.asus.wook3.net.SatinApiService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asus on 2018/5/26.
 */
@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder(){
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS);
    }
    @Provides
    SatinApi provideSatinApi(OkHttpClient.Builder builder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.apiopen.top/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
        SatinApiService satinApiService = retrofit.create(SatinApiService.class);
        return SatinApi.getSatinApi(satinApiService);

    }

}
