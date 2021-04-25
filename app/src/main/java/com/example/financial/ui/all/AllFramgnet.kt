package com.example.financial.ui.all

import com.example.financial.R
import com.example.financial.base.Product
import com.example.frame_library.mvp.BaseFragment
import java.security.AllPermission

class AllFramgnet :BaseFragment<AllPresenter>(),IAllCanter.View {
    override fun bandLayoutId(): Int = R.layout.fragment_all

    override fun initView() {
        attaPresenter(AllPresenter(AllModle(),this))
    }

    override fun initData() {
        mPresenter!!.lodingData()
    }

    override fun OnLeftClickListenter() {
        TODO("Not yet implemented")
    }

    override fun OnTitleClickListenter() {
        TODO("Not yet implemented")
    }

    override fun OnRightClickListenter() {
        TODO("Not yet implemented")
    }

    //override fun setPresenter(): AllPresenter = AllPresenter(AllModle(),this)

    override fun onLodinData(list: List<Product>) {

    }
}