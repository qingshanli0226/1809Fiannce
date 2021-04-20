package com.example.net;

import com.example.model.HomeBean;
import com.example.model.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FiannceApiService {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();
}
