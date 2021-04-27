package com.example.frame_library.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.frame_library.view.LodingPage
import com.example.frame_library.view.ToBar

interface IView {
    fun showTaos(meang: String)
    fun showLodin()
    fun hideLodin()
}

interface IActivity : IView {
    fun bandLayoutId(): Int
    fun initView();
    fun initData();
}

abstract class BaseActitvty<P : IPresneter> : AppCompatActivity(), IActivity {
    protected var lodingPage: LodingPage? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lodingPage = object : LodingPage(this) {
            override fun getSuccessLayoutId(): Int {
                return bandLayoutId()
            }
        }
        setContentView(lodingPage)
        initView()
        initData()
    }

    protected var mPresenter: P? = null

    //protected abstract fun setPresneter():P?
    protected fun attaPresenter(mPresenter: P) {
        this.mPresenter = mPresenter
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.destroy()
        }
    }

    override fun showTaos(meang: String) {
        Toast.makeText(this, meang, Toast.LENGTH_LONG).show()
        lodingPage!!.showError(meang)
    }

    override fun showLodin() {
        lodingPage!!.showLodingView()
    }

    override fun hideLodin() {
        lodingPage!!.showSuccessLayout()
    }

}

abstract class BaseFragment<P : IPresneter> : IActivity, Fragment(), ToBar.OnClickListener {

    protected var mPresenter: P? = null

    //    by lazy {
//        setPresenter();
//    }
    private var mToBar: ToBar? = null
//    by lazy {
//        initToBar().setonClickListener(this)
//    }

    protected var lodingPage: LodingPage? = null

    //abstract fun initToBar(): ToBar
    protected fun attaToBar(toBar: ToBar) {
        mToBar = toBar
        mToBar!!.setonClickListener(this)
    }

    protected fun attaPresenter(mPresenter: P) {
        this.mPresenter = mPresenter
    }

    //protected abstract fun setPresenter():P

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        lodingPage = object : LodingPage(context) {
            override fun getSuccessLayoutId(): Int {
                return bandLayoutId()
            }
        }
        return lodingPage
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun showLodin() {
        lodingPage!!.showLodingView()
    }

    override fun showTaos(meang: String) {
        Toast.makeText(activity, meang, Toast.LENGTH_LONG).show()
        lodingPage!!.showError(meang)
    }

    override fun hideLodin() {
        lodingPage!!.showSuccessLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.destroy()
        }
    }
}