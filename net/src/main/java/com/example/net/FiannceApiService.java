package com.example.net;

import com.example.net.model.AllProductBean;
import com.example.net.model.HoemBean;
import com.example.net.model.VersionBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;

public interface FiannceApiService {

    @GET(AppNetConfig.UPDATE)
    Observable<VersionBean> getServerVersion();

    @GET(AppNetConfig.INDEX)
    Observable<HoemBean> getHomeData();


    @GET(AppNetConfig.PRODUCT)
    Observable<AllProductBean> getAllProduct();
}
