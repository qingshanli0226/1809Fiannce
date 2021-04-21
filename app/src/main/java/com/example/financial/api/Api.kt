package com.example.financial.api

import com.example.financial.base.Product
import com.example.financial.base.ResultX
import com.example.financial.base.UpData
import com.example.net_library.http.InterUrl
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET(InterUrl.UPDATE)
    fun getVersion(): Observable<UpData>

    @GET(InterUrl.PRODUCT)
    fun product():Observable<Product<ResultX>>
}