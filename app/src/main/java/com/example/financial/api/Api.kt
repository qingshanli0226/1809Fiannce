package com.example.financial.api

import com.example.financial.base.*
import com.example.net_library.http.InterUrl
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface Api {
    @GET(InterUrl.UPDATE)
    fun getVersion(): Observable<Request<UpData>>

    @GET(InterUrl.PRODUCT)
    fun product(): Observable<RequestList<Product>>

    @GET(InterUrl.INDEX)
    fun index(): Observable<Request<Index>>

    @POST("autoLogin")
    @FormUrlEncoded
    fun autoLogin(@Field("token")token:String):Observable<Request<AutoLogin>>

    @GET
    @Streaming
    fun download(@Url url:String):Observable<ResponseBody>
}