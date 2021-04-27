package com.example.financial.api

import com.example.financial.base.*
import com.example.net_library.http.InterUrl
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET(InterUrl.UPDATE)
    fun getVersion(): Observable<Request<UpData>>

    @GET(InterUrl.PRODUCT)
    fun product(): Observable<RequestList<Product>>

    @GET(InterUrl.INDEX)
    fun index(): Observable<Request<Index>>
}