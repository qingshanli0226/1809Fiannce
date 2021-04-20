package com.example.net;

import com.example.net.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpApiService {
    @GET(AppNetConfig.INDEX)
    Observable<HomeBean> getHomeData();
}
