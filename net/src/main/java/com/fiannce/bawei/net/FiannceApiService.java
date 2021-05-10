package com.fiannce.bawei.net;



import com.fiannce.bawei.net.config.AppNetConfig;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.ProductBean;
import com.fiannce.bawei.net.mode.VersionBean;
import com.fiannce.bawei.net.user.login.bean.LoginBean;
import com.fiannce.bawei.net.user.register.bean.RegisterBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

//认为是IMode
public interface FiannceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<ProductBean> getProductData();

    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> getRegister(@Field("name") String name,@Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> getLogin(@Field("name") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST(AppNetConfig.AUTOLOGIN)
    Observable<LoginBean> getAutoLogin(@Field("token") String token);

    @GET
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url);

}
