package com.example.financial.ui.mine

import android.content.Intent
import com.example.financial.R
import com.example.frame_library.mvp.BaseFragment
import com.example.frame_library.mvp.IPresneter
import com.example.frame_library.view.ToBar
import com.example.user_library.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment<IPresneter>() {
    override fun bandLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
        attaToBar(fragment_mine_ToBar)
    }

    override fun initData() {
        startActivity(Intent(activity,LoginActivity::class.java))
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