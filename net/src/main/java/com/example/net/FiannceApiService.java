package com.example.net;

import com.example.model.HomeBean;
import com.example.model.LoginBean;
import com.example.model.ProductBean;
import com.example.model.RegisterBean;
import com.example.model.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    @POST("login?name=1010&password=1010")
    Observable<LoginBean> postLogin(@Field("name") String name, @Field("password") String password);

}
