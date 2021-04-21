package com.example.financial.ui.home

import com.example.financial.base.Product
import com.example.financial.base.ResultX
import com.example.financial.base.UpData
import com.example.net_library.http.observer.BaseObserver
import com.example.frame_library.mvp.BasePresenter

class HomePresenter(mModle:HomeModle,mView:HomeCanter.View):BasePresenter<HomeCanter.View,HomeCanter.Modle>(mView,mModle) {

    fun getVersion(){
        mModle!!.getVersion(object : BaseObserver<UpData>() {
            override fun sure(t: UpData) {
                mView!!.IsUpdate(t.result.versionCode)
            }

            override fun fille(meag: String) {
                super.fille(meag)
                mView!!.showTaos(meag)
            }

            override fun onStart() {
                super.onStart()
                mModle!!.addObserver(this)
            }
        })
    }

    fun LoginData(){
        mModle!!.LodinData(object : BaseObserver<Product<ResultX>>() {
            override fun sure(t: Product<ResultX>) {
                mView!!.onLodinData(t)
            }

            override fun onStart() {
                super.onStart()
                mModle!!.addObserver(this)
            }

            override fun fille(meag: String) {
                super.fille(meag)
                mView!!.showTaos(meag)
            }
        })
    }

}