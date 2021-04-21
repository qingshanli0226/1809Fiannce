package com.example.net;

import com.example.net.bean.HomeBean;
import com.example.net.bean.ProductBean;
import com.example.net.bean.UpdateBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpApiService {
    @GET(AppNetConfig.INDEX)
    Observable<HomeBean> getHomeData();
    @GET(AppNetConfig.UPDATE)
    Observable<UpdateBean> getAppUpdate();

    @GET(AppNetConfig.PRODUCT)
    Observable<ProductBean> getProduct();
}
