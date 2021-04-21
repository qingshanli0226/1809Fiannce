package com.example.financial.ui.welcome

import com.example.financial.api.Api
import com.example.financial.base.*
import com.example.net_library.http.RetrofitManger
import com.example.net_library.http.observer.BaseObserver
import com.example.frame_library.mvp.BaseModle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WelcomeModle:BaseModle(),WelcomeCanter.Modle {
    override fun getVersion(callObserver: BaseObserver<Request<UpData>>) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .getVersion()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callObserver)
    }

    override fun requestData(callObserver: BaseObserver<Request<Index>>) {
        RetrofitManger.retrofit
            .create(Api::class.java)
            .index()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callObserver)
    }

}