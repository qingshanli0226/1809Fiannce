package com.example.frame_library.mvp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

abstract class BaseActitvty <P :IPresneter>: AppCompatActivity(), IActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bandLayoutId())
        initView()
        initData()
    }

    protected val mPresenter:P by lazy {
        setPresneter()
    }

    protected abstract fun setPresneter():P

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter!=null){
            mPresenter!!.destroy()
        }
    }

    override fun showTaos(meang: String) {
        Toast.makeText(this, meang, Toast.LENGTH_LONG).show()
    }

    override fun showLodin() {

    }

    override fun hideLodin() {

    }

}

abstract class BaseFragment<P:IPresneter>:IActivity,Fragment(),ToBar.OnClickListener{

    protected val mPresenter:P by lazy {
        setPresenter();
    }
    private val mToBar: ToBar by lazy {
        initToBar().setonClickListener(this)
    }

    abstract fun initToBar(): ToBar

    protected abstract fun setPresenter():P

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(bandLayoutId(),container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun showLodin() {

    }

    override fun showTaos(meang: String) {
        Toast.makeText(activity,meang,Toast.LENGTH_LONG).show()
    }

    override fun hideLodin() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter!=null){
            mPresenter!!.destroy()
        }
    }
}