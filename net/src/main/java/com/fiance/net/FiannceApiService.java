package com.fiance.net;

import com.fiance.net.mode.HomeBean;
import com.fiance.net.mode.LcBean;
import com.fiance.net.mode.LoginBean;
import com.fiance.net.mode.RegisterBean;
import com.fiance.net.mode.VersionBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
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
    Observable<LcBean> getLiCaiData();

    @FormUrlEncoded
    @POST("register?")
    Observable<RegisterBean> getRegisterData(@Field("name")String name,@Field("password")String password);

    @FormUrlEncoded
    @POST("login?")
    Observable<LoginBean> getLoginData(@Field("name")String name,@Field("password")String password);

    @GET
    @Streaming
        //防止占用内存过多，避免OOM问题也就是内存溢出
    Observable<ResponseBody> downloadFile(@Url String url);

}
