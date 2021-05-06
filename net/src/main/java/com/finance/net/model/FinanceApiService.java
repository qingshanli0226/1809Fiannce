package com.finance.net.model;


import com.finance.net.bean.HomeBean;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.ProductBean;
import com.finance.net.bean.RegisterBean;
import com.finance.net.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FinanceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();
    //http://49.233.0.68:8080/atguigu/json/P2PInvest/product.json
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<ProductBean> getProduct();

    //    http://49.233.0.68:8080/register  注册
    @POST("register?")
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(@Field("name")String name, @Field("password")String password);

    //    http://49.233.0.68:8080/login //登录
    @POST("login?")
    @FormUrlEncoded
    Observable<LoginBean> getLogin(@Field("name")String name, @Field("password")String password);

    @FormUrlEncoded
    @POST("autoLogin")
    Observable<LoginBean> getAutoLogin(@Field("token")String token);

}
