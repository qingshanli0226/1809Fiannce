package com.example.financial.ui.product

import com.example.financial.base.Product
import com.example.financial.base.ResultX
import com.example.frame_library.mvp.BasePresenter
import com.example.net_library.http.observer.BaseObserver

class ProductPresenter(mView:ProductCanter.View,mModle:ProductModle):BasePresenter<ProductCanter.View,ProductCanter.Modle>(mView, mModle) {

    fun LoginData(){
        mModle!!.LodinData(object : BaseObserver<Product<ResultX>>() {
            override fun sure(t: Product<ResultX>) {
                mView!!.onLodinData(t.result)
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