package com.finance.net.model;


import com.finance.net.bean.HomeBean;
import com.finance.net.bean.ProductBean;
import com.finance.net.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FinanceApiService {

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<HomeBean> getHomeData();
    @GET("atguigu/json/P2PInvest/update.json")
    Observable<VersionBean> getServerVersion();
    //http://49.233.0.68:8080/atguigu/json/P2PInvest/product.json
    @GET("atguigu/json/P2PInvest/product.json")
    Observable<ProductBean> getProduct();

}
