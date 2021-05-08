package com.fiannce.bawei.net;

import com.fiannce.bawei.net.model.HomeBean;
import com.fiannce.bawei.net.model.Libean;
import com.fiannce.bawei.net.model.LoginBean;
import com.fiannce.bawei.net.model.RegisterBean;
import com.fiannce.bawei.net.model.VersionBean;

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

    @GET
    @Streaming
    //防止占用内存过多，避免OOM问题也就是内存溢出
    Observable<ResponseBody> downloadFile(@Url String url);

}
