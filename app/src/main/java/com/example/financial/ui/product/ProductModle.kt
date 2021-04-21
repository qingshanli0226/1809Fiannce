package com.example.financial.ui.product

import com.example.financial.api.Api
import com.example.financial.base.Product
import com.example.financial.base.ResultX
import com.example.frame_library.mvp.BaseModle
import com.example.net_library.http.RetrofitManger
import com.example.net_library.http.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductModle:BaseModle(),ProductCanter.Modle {
    override fun LodinData(callObservable: BaseObserver<Product<ResultX>>) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .product()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callObservable)
    }
}