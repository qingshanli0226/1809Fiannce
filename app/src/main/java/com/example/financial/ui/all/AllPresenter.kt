package com.example.financial.ui.all

import com.example.financial.base.Product
import com.example.financial.base.RequestList
import com.example.frame_library.mvp.BasePresenter
import com.example.net_library.http.observer.BaseObserver

class AllPresenter(mModle: AllModle, mView:IAllCanter.View):BasePresenter<IAllCanter.View,IAllCanter.Modle>(mView, mModle) {
    fun lodingData(){
        mModle!!.LodingData(object : BaseObserver<RequestList<Product>>() {
            override fun sure(t: RequestList<Product>) {
                mView!!.onLodinData(t.result)
            }

            override fun onComplete() {
                super.onComplete()
                mView!!.hideLodin()
            }

            override fun onStart() {
                super.onStart()
                mView!!.showLodin()
                mModle!!.addObserver(this)
            }

            override fun fille(meag: String) {
                super.fille(meag)
                mView!!.showTaos(meag)
            }
        })
    }
}