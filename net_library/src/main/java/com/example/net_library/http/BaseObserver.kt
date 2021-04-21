package com.example.net_library.http.observer

import com.blankj.utilcode.util.LogUtils
import io.reactivex.observers.DisposableObserver

interface ICallObserver<T> {
    fun sure(t:T);
    fun fille(meag:String);
}

abstract class BaseObserver<T>:DisposableObserver<T>(),ICallObserver<T>{

    override fun onNext(t: T) {
        sure(t)
    }

    override fun onError(e: Throwable) {
        fille(e.message!!)
    }

    override fun onComplete() {

    }

    override fun fille(meag: String) {
       LogUtils.e(meag)
    }

}