package com.example.financial.ui.home

import com.example.financial.R
import com.example.frame_library.mvp.BaseFragment
import com.example.frame_library.mvp.IPresneter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment:BaseFragment<IPresneter>() {
    override fun bandLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        frgemtn_home_progressView.setProgress(80f,false)
    }

    override fun initData() {

    }

    override fun setPresenter(): IPresneter {
        TODO("Not yet implemented")
    }
}