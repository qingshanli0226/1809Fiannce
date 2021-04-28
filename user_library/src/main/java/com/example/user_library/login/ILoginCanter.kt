package com.example.user_library.login

import com.example.frame_library.mvp.IModle
import com.example.frame_library.mvp.IView
import com.example.net_library.http.observer.BaseObserver
import com.example.user_library.base.Requst
import com.example.user_library.base.User

interface ILoginCanter {
    interface Modle : IModle {
        fun login(username:String,password:String,callObserver: BaseObserver<Requst<User>>)
    }

    interface View : IView {
        fun getUsername(): String
        fun getPassword(): String
        fun onRequst(requst: Requst<User>)
    }
}