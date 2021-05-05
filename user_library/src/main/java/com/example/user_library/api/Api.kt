package com.example.user_library.api

import com.example.net_library.http.InterUrl
import com.example.user_library.base.Requst
import com.example.user_library.base.User
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @POST(InterUrl.LOGIN)
    @FormUrlEncoded
    fun login(@Field("name") name:String,@Field("password")password:String):Observable<Requst<User>>
}