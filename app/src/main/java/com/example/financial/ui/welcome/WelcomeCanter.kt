package com.example.financial.ui.welcome

import com.example.financial.base.Index
import com.example.financial.base.Request
import com.example.financial.base.RequestList
import com.example.financial.base.UpData
import com.example.net_library.http.observer.BaseObserver
import com.example.frame_library.mvp.IModle
import com.example.frame_library.mvp.IView

interface WelcomeCanter {
    interface Modle :IModle{
        fun getVersion(callObserver:BaseObserver<Request<UpData>>)
        fun requestData(callObserver: BaseObserver<Request<Index>>);
    }

    interface View:IView{
        fun onUpdate(upData: UpData)
        fun onData(index: Index);
    }
}