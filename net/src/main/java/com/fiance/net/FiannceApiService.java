package com.fiance.net;

import com.fiance.net.mode.HomeBean;
import com.fiance.net.mode.LcBean;
import com.fiance.net.mode.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<LcBean> getLiCaiData();

}