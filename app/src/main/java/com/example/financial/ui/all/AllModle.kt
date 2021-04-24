package com.example.financial.ui.all

import com.example.financial.api.Api
import com.example.financial.base.Product
import com.example.financial.base.RequestList
import com.example.frame_library.mvp.BaseModle
import com.example.net_library.http.RetrofitManger
import com.example.net_library.http.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllModle:BaseModle(),IAllCanter.Modle {
    override fun LodingData(callObserver: BaseObserver<RequestList<Product>>) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .product()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(callObserver)
    }
}