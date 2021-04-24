package com.example.financial.ui.all

import com.example.financial.base.Product
import com.example.financial.base.RequestList
import com.example.frame_library.mvp.IModle
import com.example.frame_library.mvp.IView
import com.example.net_library.http.observer.BaseObserver

interface IAllCanter {
    interface Modle : IModle {
        fun LodingData(callObserver: BaseObserver<RequestList<Product>>)
    }

    interface View : IView {
        fun onLodinData(list: List<Product>);
    }
}