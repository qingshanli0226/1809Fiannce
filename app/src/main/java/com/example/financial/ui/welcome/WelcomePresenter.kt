package com.example.financial.ui.welcome

import com.example.financial.base.*
import com.example.net_library.http.observer.BaseObserver
import com.example.frame_library.mvp.BasePresenter

class WelcomePresenter(mModle: WelcomeModle, mView: IWelcomeCanter.View) :
    BasePresenter<IWelcomeCanter.View, IWelcomeCanter.Modle>(mView, mModle) {

    fun getVersion() {
        mModle!!.getVersion(object : BaseObserver<Request<UpData>>() {
            override fun sure(t: Request<UpData>) {
                mView!!.onUpdate(t.result)
            }

            override fun fille(meag: String) {
                super.fille(meag)
                //mView!!.showTaos(meag)
            }

            override fun onStart() {
                super.onStart()
                mModle!!.addObserver(this)
                //mView!!.showLodin()
            }

            override fun onComplete() {
                super.onComplete()
                //mView!!.hideLodin()
            }
        })

        mModle!!.requestData(object : BaseObserver<Request<Index>>() {
            override fun sure(t: Request<Index>) {
                mView!!.onData(t.result)
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