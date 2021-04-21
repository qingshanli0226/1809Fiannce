package com.example.financial.ui.home

import com.example.financial.base.Product
import com.example.financial.base.ResultX
import com.example.financial.base.UpData
import com.example.net_library.http.observer.BaseObserver
import com.example.frame_library.mvp.IModle
import com.example.frame_library.mvp.IView

interface HomeCanter {
    interface Modle :IModle{
        fun getVersion(callObserver:BaseObserver<UpData>)
        fun LodinData(callObserver: BaseObserver<Product<ResultX>>);
    }

    interface View:IView{
        fun IsUpdate(versionCode:Int)
        fun onLodinData(product: Product<ResultX>)
    }
}