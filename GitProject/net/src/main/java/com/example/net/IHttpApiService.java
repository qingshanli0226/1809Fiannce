package com.example.net;

import android.app.Application;

import com.example.net.bean.GesturePassword;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.UpdateBean;

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

public interface IHttpApiService {
    @GET(AppNetConfig.INDEX)
    Observable<HomeBean> getHomeData();
    @GET(AppNetConfig.UPDATE)
    Observable<UpdateBean> getAppUpdate();

    @GET(AppNetConfig.PRODUCT)
    Observable<ProductBean> getProduct();


    @FormUrlEncoded
    @POST(AppNetConfig.USERREGISTER)
    Observable<RegisterBean> getRegister(@Field("name")String name, @Field("password")String password);


    @FormUrlEncoded
    @POST(AppNetConfig.LOGIN)
    Observable<LoginBean> getLogin(@Field("name")String name, @Field("password")String password);

    @FormUrlEncoded
    @POST(AppNetConfig.AUTOLOGIN)
    Observable<LoginBean> getAutoLogin(@Field("token")String token);

    //下载
    @Streaming
    @GET
    Observable<ResponseBody> getDownLoad(@Url String url);


    //验证密码
    @POST(AppNetConfig.SETGESTUREPASSWORD)
    Observable<GesturePassword> setGesturePassword(@Body RequestBody requestBody);

}
