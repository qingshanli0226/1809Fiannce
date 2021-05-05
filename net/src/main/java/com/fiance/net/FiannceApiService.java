package com.fiance.net;

import com.fiance.net.mode.HomeBean;
import com.fiance.net.mode.LcBean;
import com.fiance.net.mode.LoginBean;
import com.fiance.net.mode.RegisterBean;
import com.fiance.net.mode.VersionBean;

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
    Observable<LcBean> getLiCaiData();

    @FormUrlEncoded
    @POST("register?")
    Observable<RegisterBean> getRegisterData(@Field("name")String name,@Field("password")String password);

    @FormUrlEncoded
    @POST("login?")
    Observable<LoginBean> getLoginData(@Field("name")String name,@Field("password")String password);



}
