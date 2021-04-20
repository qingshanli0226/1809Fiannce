package com.example.net;

import com.example.net.mode.HomeBean;
import com.example.net.mode.VersionBean;

import io.reactivex.Observable;
import okhttp3.internal.Version;
import retrofit2.http.GET;

public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();
}
