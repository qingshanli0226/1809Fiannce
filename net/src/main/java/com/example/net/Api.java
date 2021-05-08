package com.example.net;

import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.UpdataBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface Api {

@GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();
@GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdataBean>getUpdataData();
@GET("atguigu/json/P2PInvest/product.json")
    Observable<ProductBean>getProductData();
@FormUrlEncoded
@POST("register")
    Observable<RegisterBean> getReisterData(@Field("name")String name,@Field("password")String passord);
@FormUrlEncoded
@POST("login")
    Observable<LoginBean>getLoginData(@Field("name")String name,@Field("password")String password);
@FormUrlEncoded
    @POST("autoLogin")
    Observable<LoginBean> getAutoData(@Field("token")String token);
@GET
@Streaming//防止占用内存多，避免oom内存溢出
Observable<ResponseBody> downloadFile(@Url String url);
}

