package com.example.net;

import com.example.net.bean.AllMoneyBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FiannceApiModel {

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getVersionData();
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHome();
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllMoneyBean> getAll();
    //注册
    @FormUrlEncoded
    @POST("register?")
    Observable<RegisterBean> getRegister(@Field("name") String name,@Field("password") String pwd);
}
