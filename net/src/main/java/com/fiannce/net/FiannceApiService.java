package com.fiannce.net;

import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.InvestBean;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.RegisterBean;
import com.fiannce.net.mode.VersionBean;

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
    Observable<InvestBean> getProductData();

    @POST("register?")
    @FormUrlEncoded
    Observable<RegisterBean> getRegisterData(@Field("name")String name,@Field("password")String password);

    @POST("login?")
    @FormUrlEncoded
    Observable<LoginBean> getLoginData(@Field("name")String name, @Field("password")String password);

    @FormUrlEncoded
    @POST(AppNetConfig.AUTOLOGIN)
    Observable<LoginBean> getAutoLogin(@Field("token")String token);
}
