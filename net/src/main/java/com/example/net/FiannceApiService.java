package com.example.net;

import com.example.net.model.AllProductBean;
import com.example.net.model.HoemBean;
import com.example.net.model.LoginBean;
import com.example.net.model.RegisterBean;
import com.example.net.model.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LoginBean> getAutoLoginData(@Field("token") String token);

}
