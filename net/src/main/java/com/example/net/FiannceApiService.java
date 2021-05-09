package com.example.net;

import com.example.net.mode.AllBean;
import com.example.net.mode.GestureBean;
import com.example.net.mode.HomeBean;
import com.example.net.mode.LogBean;
import com.example.net.mode.RegBean;
import com.example.net.mode.VersionBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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

    @FormUrlEncoded
    @POST("login")
    Observable<LogBean> LogData(@Field("name") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LogBean> AutoData(@Field("token") String token);

    @GET
    @Streaming
    Observable<ResponseBody> dowloadFile(@Url String url);

    @POST("setGesturePassword")
    Observable<GestureBean> setGesturePwd(@Body RequestBody gPasswordBody);
}
