package com.example.financial.ui.home

import com.example.financial.api.Api
import com.example.financial.base.Product
import com.example.financial.base.ResultX
import com.example.financial.base.UpData
import com.example.net_library.http.RetrofitManger
import com.example.net_library.http.observer.BaseObserver
import com.example.frame_library.mvp.BaseModle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HomeModle:BaseModle(),HomeCanter.Modle {
    override fun getVersion(callObserver: BaseObserver<UpData>) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .getVersion()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callObserver)
    }

    override fun LodinData(callObservable: BaseObserver<Product<ResultX>>) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .product()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callObservable)
    }

}