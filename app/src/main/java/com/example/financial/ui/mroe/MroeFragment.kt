package com.example.financial.ui.mroe

import com.example.financial.R
import com.example.frame_library.mvp.BaseFragment
import com.example.frame_library.mvp.IPresneter
import com.example.frame_library.view.ToBar
import kotlinx.android.synthetic.main.fragment_mroe.*

class MroeFragment : BaseFragment<IPresneter>() {
    override fun bandLayoutId(): Int = R.layout.fragment_mroe

    override fun initView() {
        attaToBar(fragment_mroe_ToBar)
    }

    override fun initData() {

    }

    override fun OnLeftClickListenter() {

    }

    override fun OnTitleClickListenter() {

    }

    override fun OnRightClickListenter() {

    }

//    override fun setPresenter(): IPresneter {
//        TODO("Not yet implemented")
//    }
}