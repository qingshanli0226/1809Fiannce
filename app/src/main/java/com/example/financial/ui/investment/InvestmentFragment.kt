package com.example.financial.ui.investment

import com.example.financial.R
import com.example.frame_library.mvp.BaseFragment
import com.example.frame_library.mvp.IPresneter
import com.example.frame_library.view.ToBar
import kotlinx.android.synthetic.main.fragment_investment.*

class InvestmentFragment:BaseFragment<IPresneter>() {
    override fun bandLayoutId(): Int= R.layout.fragment_investment

    override fun initView() {

    }

    override fun initData() {

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

    override fun setPresenter(): IPresneter {
        TODO("Not yet implemented")
    }

    override fun initToBar(): ToBar {
        return fragment_investment_ToBar
    }
}