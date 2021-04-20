package com.example.net;

import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

@GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();
@GET("atguigu/json/P2PInvest/update.json")
    Observable<UpdataBean>getUpdataData();
}
