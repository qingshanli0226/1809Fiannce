package com.example.net;

import com.example.model.AutoLoginBean;
import com.example.model.ExitLoginBean;
import com.example.model.GesturePasswordBean;
import com.example.model.HomeBean;
import com.example.model.LoginBean;
import com.example.model.ProductBean;
import com.example.model.RegisterBean;
import com.example.model.VersionBean;

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
    Observable<ProductBean> getProductData();

    @FormUrlEncoded
    @POST("register")
    Observable<RegisterBean> postRegister(@Field("name") String name,@Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> postLogin(@Field("name") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST("autoLogin")
    Observable<AutoLoginBean> postAutoLogin(@Field("token") String token);

    @POST("logout")
    Observable<ExitLoginBean> postExitLogin();

    //设置手势密码
    @POST("setGesturePassword")
    Observable<GesturePasswordBean> setGesturePassword(@Body RequestBody requestBody);

    //验证手势密码
    @POST("loginByGesturePassword")
    Observable<GesturePasswordBean> setLoginByGesturePassword(@Body RequestBody requestBody);

    //清除手势密码
    @POST("clearGesturePassword")
    Observable<GesturePasswordBean> clearGesturePassword(@Body RequestBody requestBody);

    //下载文件
    @GET
    @Streaming
    //防止占用内存过多，避免OOM问题也就是内存溢出
    Observable<ResponseBody> downLoadFile(@Url String url);

}
