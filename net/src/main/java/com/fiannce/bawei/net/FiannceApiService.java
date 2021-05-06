package com.fiannce.bawei.net;

import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.Libean;
import com.fiannce.bawei.net.model.LoginBean;
import com.fiannce.bawei.net.model.RegisterBean;
import com.fiannce.bawei.net.model.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomdeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<Libean> getLiData();

    @POST("login")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("name") String name,@Field("password")String password);

    @POST("autoLogin")
    @FormUrlEncoded
    Observable<LoginBean> getToken(@Field("token") String token);

    @POST("register")
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(@Field("name") String name, @Field("password")String password);

}
