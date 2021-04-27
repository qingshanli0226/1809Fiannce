package com.example.network;

import com.example.network.model.AllBean;
import com.example.network.model.HomeBean;
import com.example.network.model.RegBean;
import com.example.network.model.UpdateBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> HomeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdateBean> UpdateData();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllBean> AllData();

    @FormUrlEncoded
    @POST("register")
    Observable<RegBean> RegData(@Field("name") String name,@Field("password") String password);



}
