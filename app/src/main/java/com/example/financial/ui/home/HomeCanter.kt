package com.example.financial.ui.home

import com.example.financial.base.Index
import com.example.financial.base.Request
import com.example.frame_library.mvp.IModle
import com.example.frame_library.mvp.IView
import com.example.net_library.http.observer.BaseObserver

interface HomeCanter {
    interface Modle :IModle{
        fun requestData(callObserver: BaseObserver<Request<Index>>);
    }
    interface View :IView{
        fun onData(index: Index);
    }
}