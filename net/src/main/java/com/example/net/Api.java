package com.example.net;

import com.example.net.bean.HomeBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.RegisterBean;
import com.example.net.bean.UpdataBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

@GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();
@GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdataBean>getUpdataData();
@GET("atguigu/json/P2PInvest/product.json")
    Observable<ProductBean>getProductData();
@FormUrlEncoded
@POST("register")
    Observable<RegisterBean> getReisterData(@Field("username")String username,@Field("password")String passord);
}
