package com.example.net;

import com.example.net.model.AllProductBean;
import com.example.net.model.HoemBean;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.UnlockBean;
import com.example.net.model.VersionBean;

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

    @GET(AppNetConfig.UPDATE)
    Observable<VersionBean> getServerVersion();

    @GET(AppNetConfig.INDEX)
    Observable<HoemBean> getHomeData();


    @GET(AppNetConfig.PRODUCT)
    Observable<AllProductBean> getAllProduct();


    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> getRegisterData(@Field("name") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> getLoginData(@Field("name") String username, @Field("password") String password);

    @POST("logout")
    Observable<UnlockBean> getLoginOut();


    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LoginBean> getAutoLoginData(@Field("token") String token);


    @GET
    @Streaming
        //防止占用内存过多，避免OOM问题也就是内存溢出
    Observable<ResponseBody> downloadFile(@Url String url);

    @POST("setGesturePassword")
    Observable<UnlockBean> setUnlockData(@Body RequestBody gPasswordBody);

    @POST("loginByGesturePassword")
    Observable<UnlockBean> verifyUnlockData(@Body RequestBody gPasswordBody);

    @POST("clearByGesturePassword")
    Observable<UnlockBean> clearUnlockData(@Body RequestBody gPasswordBody);


}
