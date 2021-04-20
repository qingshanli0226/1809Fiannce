package com.example.net;

import com.example.net.model.HoemBean;
import com.example.net.model.VersionBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;

public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<HoemBean> getHomeData();

}
