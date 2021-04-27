package com.fiannce.net;

import com.fiannce.net.mode.AllMoneyBean;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.RegisterBean;
import com.fiannce.net.mode.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllMoneyBean> getAllmoney();

    @POST("register")
    Observable<RegisterBean> getRegister(@Query("name")String name,@Query("password") String password);

    @POST("login")
    Observable<LoginBean> getLogin();


}
