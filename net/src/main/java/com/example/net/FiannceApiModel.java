package com.example.net;

import com.example.net.bean.AllMoneyBean;
import com.example.net.bean.AutoBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.LogoutBean;
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
    //版本
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getVersionData();

    //首页数据
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> gettHome();

    //第二个页面数据
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllMoneyBean> getAll();

    //注册
    @FormUrlEncoded
    @POST("register?")
    Observable<RegisterBean> getRegister(@Field("name") String name, @Field("password") String pwd);

    //退出登陆
    @FormUrlEncoded
    @POST("logout?")
    Observable<LogoutBean> getLogout(@Field("token") String token);


    //登录
    @FormUrlEncoded
    @POST("login?")
    Observable<LoginBean> getLogin(@Field("name") String name, @Field("password") String pwd);

    //自动登录
    @FormUrlEncoded
    @POST("autoLogin?")
    Observable<AutoBean> getAuto(@Field("token") String token);

    //下载
    @GET
    @Streaming
    Observable<ResponseBody> DownLoadApk(@Url String url);

}
