package com.example.user_library.login

import com.example.frame_library.mvp.BaseModle
import com.example.net_library.http.RetrofitManger
import com.example.net_library.http.observer.BaseObserver
import com.example.user_library.Api
import com.example.user_library.base.Requst
import com.example.user_library.base.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginModle :BaseModle(),ILoginCanter.Modle {
    override fun login(username:String,password:String,callObserver: BaseObserver<Requst<User>>) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .login(username,password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(callObserver)
    }
}