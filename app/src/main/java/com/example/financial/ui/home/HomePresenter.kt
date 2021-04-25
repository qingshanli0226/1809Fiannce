package com.example.financial.ui.home

import com.example.financial.base.Index
import com.example.financial.base.Request
import com.example.frame_library.mvp.BasePresenter
import com.example.net_library.http.observer.BaseObserver

class HomePresenter(mModle:HomeModle,mView:HomeCanter.View):BasePresenter<HomeCanter.View,HomeCanter.Modle>(mView,mModle) {
    fun getData(){
        mModle!!.requestData(object : BaseObserver<Request<Index>>() {
            override fun sure(t: Request<Index>) {
                mView!!.onData(t.result)
            }

            override fun onStart() {
                super.onStart()
                mModle!!.addObserver(this)
                mView!!.showLodin()
            }

            override fun onComplete() {
                super.onComplete()
                mView!!.hideLodin()
            }

            override fun fille(meag: String) {
                super.fille(meag)
                mView!!.showTaos(meag)
            }
        })
    }
}