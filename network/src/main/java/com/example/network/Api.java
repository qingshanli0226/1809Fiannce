package com.example.network;

import com.example.network.model.AllBean;
import com.example.network.model.HomeBean;
import com.example.network.model.UpdateBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> HomeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdateBean> UpdateData();
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<AllBean> AllData();

}
