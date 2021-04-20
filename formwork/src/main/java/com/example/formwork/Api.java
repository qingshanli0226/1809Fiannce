package com.example.formwork;

import com.example.formwork.model.HomeBean;
import com.example.formwork.model.UpdateBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {
    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> HomeData();

    @GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdateBean> UpdateData();

}
