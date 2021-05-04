package com.example.net;

import com.example.net.mode.AllBean;
import com.example.net.mode.HomeBean;
import com.example.net.mode.RegBean;
import com.example.net.mode.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllBean> AllData();

    @FormUrlEncoded
    @POST("register")
    Observable<RegBean> RegData(@Field("name") String name, @Field("password") String password);


}
