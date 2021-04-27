package com.example.financial.ui.home

import com.example.financial.api.Api
import com.example.financial.base.Index
import com.example.financial.base.Request
import com.example.frame_library.mvp.BaseModle
import com.example.net_library.http.RetrofitManger
import com.example.net_library.http.observer.BaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeModle : BaseModle(), IHomeCanter.Modle {

    override fun requestData(callObserver: BaseObserver<Request<Index>>) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .index()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callObserver)
    }

}