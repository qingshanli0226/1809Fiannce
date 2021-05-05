package com.example.user_library.registered

import com.example.frame_library.mvp.IModle
import com.example.frame_library.mvp.IView

interface IRegisteredCanter {
    interface Modle : IModle {
        fun register(username:String,password:String,)
    }

    interface View : IView {}
}