package com.example.frame_library.mvp

interface IPresneter {
    fun destroy()
}

abstract class BasePresenter<V : IView, M : IModle>(
    protected var mView: V?,
    protected var mModle: M?,
) : IPresneter {

    override fun destroy() {
        if (mModle != null) {
            mModle!!.destroy()
            mModle = null
        }
        mView = null
    }

}