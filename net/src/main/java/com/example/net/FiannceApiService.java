package com.example.net;

import com.example.net.mode.AllfinancialBean;
import com.example.net.mode.AutoLoginBean;
import com.example.net.mode.HomeBean;
import com.example.net.mode.LoginBean;
import com.example.net.mode.RegisterBean;
import com.example.net.mode.VersionBean;

import io.reactivex.Observable;
import okhttp3.internal.Version;
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
    Observable<AllfinancialBean> getAllfinancial();

    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> getLogin(@Field("name")String name,@Field("password")String password);

    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> getRegister(@Field("name")String name, @Field("password")String password);

    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LoginBean> getAuto(@Field("token")String token);
}
