package com.example.network;

import com.example.network.model.AllBean;
import com.example.network.model.HomeBean;
import com.example.network.model.LogBean;
import com.example.network.model.RegBean;
import com.example.network.model.UpdateBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface Api {

    //主页
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> HomeData();

    //下载
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdateBean> UpdateData();

    //全部理财
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllBean> AllData();

    //注册
    @FormUrlEncoded
    @POST("register")
    Observable<RegBean> RegData(@Field("name") String name,@Field("password") String password);

    //登录
    @FormUrlEncoded
    @POST("login")
    Observable<LogBean> LogData(@Field("name") String name,@Field("password") String password);

    //自动登录
    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LogBean> AutoData(@Field("token") String token);

    //下载文件
    @GET
    @Streaming   //防止占用内存过多，避免OOM问题也就是内存溢出
    Observable<ResponseBody> downloadFile(@Url String url);
}
