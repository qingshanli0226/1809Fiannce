package com.example.net;

import com.example.net.bean.AllMoneyBean;
import com.example.net.bean.AutoBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.VersionBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface FiannceApiModel {

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getVersionData();

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHome();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllMoneyBean> getAll();

    @FormUrlEncoded
    @POST("register?")
    Observable<RegisterBean> getRegister(@Field("name") String name, @Field("password") String pwd);

    @FormUrlEncoded
    @POST("login?")
    Observable<LoginBean> getLogin(@Field("name") String name, @Field("password") String pwd);
    @FormUrlEncoded
    @POST("autoLogin?")
    Observable<AutoBean> getAuto(@Field("token") String token);
    //下载
    @GET
    @Streaming
    Observable<ResponseBody> downLoadApk(@Url String url);

}
