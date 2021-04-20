package com.example.net;

import com.example.net.bean.HomeBean;
import com.example.net.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FiannceApiModel {

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getVersionData();
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHome();
}
