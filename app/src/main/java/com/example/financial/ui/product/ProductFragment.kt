package com.example.financial.ui.product

import android.util.Log
import android.widget.Toast
import com.example.financial.R
import com.example.financial.base.ResultX
import com.example.frame_library.mvp.BaseFragment

class ProductFragment :BaseFragment<ProductPresenter>(),ProductCanter.View{
    override fun bandLayoutId(): Int = R.layout.fragment_product

    override fun initView() {

    }

    override fun initData() {

    }

    override fun setPresenter(): ProductPresenter = ProductPresenter(this, ProductModle())

    override fun onLodinData(list: List<ResultX>) {

    }
}