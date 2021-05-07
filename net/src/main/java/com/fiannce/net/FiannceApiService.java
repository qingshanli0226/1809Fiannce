package com.fiannce.net;

import com.fiannce.net.mode.AllMoneyBean;
import com.fiannce.net.mode.AutoLoginBean;
import com.fiannce.net.mode.HomeBean;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.RegisterBean;
import com.fiannce.net.mode.VersionBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllMoneyBean> getAllmoney();

    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> getRegister(@Field("name")String name,@Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> getLogin(@Field("name") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST("autoLogin")
    Observable<AutoLoginBean> getAutologin(@Field("token") String token);

    @GET
    @Streaming
        //防止占用内存过多，避免OOM问题也就是内存溢出
    Observable<ResponseBody> downloadFile(@Url String url);
}
