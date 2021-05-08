package com.example.network;

import com.example.common.FiannceNetConfig;
import com.example.network.model.AllBean;
import com.example.network.model.GesturePwd;
import com.example.network.model.HomeBean;
import com.example.network.model.LogBean;
import com.example.network.model.RegBean;
import com.example.network.model.UpdateBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface Api {

    //主页
    @GET(FiannceNetConfig.HOME)
    Observable<HomeBean> HomeData();

    //下载
    @GET(FiannceNetConfig.UPDATE)
    Observable<UpdateBean> UpdateData();

    //全部理财
    @GET(FiannceNetConfig.ALL)
    Observable<AllBean> AllData();

    //注册
    @FormUrlEncoded
    @POST(FiannceNetConfig.REGISTER)
    Observable<RegBean> RegData(@Field(FiannceNetConfig.NAME) String name,@Field(FiannceNetConfig.PASSWORD) String password);

    //登录
    @FormUrlEncoded
    @POST(FiannceNetConfig.LOGIN)
    Observable<LogBean> LogData(@Field(FiannceNetConfig.NAME) String name,@Field(FiannceNetConfig.PASSWORD) String password);

    //自动登录
    @FormUrlEncoded
    @POST(FiannceNetConfig.AUTO)
    Observable<LogBean> AutoData(@Field(FiannceNetConfig.TOKEN) String token);

    //下载文件
    @GET
    @Streaming   //防止占用内存过多，避免OOM问题也就是内存溢出
    Observable<ResponseBody> downloadFile(@Url String url);

    @POST(FiannceNetConfig.SETGESTUREPWD)
    Observable<GesturePwd> setGesturePwd(@Field("gPassword") String gPassword);
}
