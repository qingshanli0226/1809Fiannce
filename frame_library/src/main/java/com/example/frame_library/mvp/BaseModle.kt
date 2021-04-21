package com.example.frame_library.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface IModle{
    fun destroy()
    fun addObserver(d: Disposable);
}
abstract class BaseModle:IModle{
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    override fun destroy() {
        compositeDisposable.clear()
    }

    override fun addObserver(d: Disposable) {
        compositeDisposable.add(d)
    }

}