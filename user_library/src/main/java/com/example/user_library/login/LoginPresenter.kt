package com.example.user_library.login

import com.example.frame_library.mvp.BasePresenter
import com.example.net_library.http.observer.BaseObserver
import com.example.user_library.base.Requst
import com.example.user_library.base.User

class LoginPresenter(mView: ILoginCanter.View, mModle: LoginModle) :
    BasePresenter<ILoginCanter.View, LoginModle>(mView, mModle) {

    fun login() {
        mModle!!.login(mView!!.getUsername(),
            mView!!.getPassword(),
            object : BaseObserver<Requst<User>>() {
                override fun sure(t: Requst<User>) {
                    mView!!.onRequst(t)
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

                override fun onComplete() {
                    super.onComplete()
                    mView!!.hideLodin()
                }
            })
    }
}