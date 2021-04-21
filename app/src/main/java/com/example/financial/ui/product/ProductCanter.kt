package com.example.financial.ui.product

import com.example.financial.base.Product
import com.example.financial.base.ResultX
import com.example.frame_library.mvp.IModle
import com.example.frame_library.mvp.IView
import com.example.net_library.http.observer.BaseObserver
import io.reactivex.Observable

interface ProductCanter {
    interface Modle :IModle{
        fun LodinData(callObservable:BaseObserver<Product<ResultX>>)
    }
    interface View :IView{
        fun onLodinData(list: List<ResultX>)
    }
}