package com.fiannce.bawei.net;

import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomdeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();

}
