package com.example.financial

import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.financial.ui.home.HomeFragment
import com.example.financial.ulit.DataUlit
import com.example.frame_library.mvp.BaseActitvty
import com.example.frame_library.mvp.IPresneter
import kotlinx.android.synthetic.main.fragment_home.*

@Route(path = DataUlit.AROUTER_JUMP_MAIN)
class MainActivity : BaseActitvty<IPresneter>() ,View.OnClickListener{

    override fun bandLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        var homeFragment = HomeFragment()
        var fragment = Fragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.act_main_LL,homeFragment,"0")
            .add(R.id.act_main_LL,fragment,"1")
            .commit()

        supportFragmentManager
            .beginTransaction()
            .show(homeFragment)
            .hide(fragment)
            .commit()
    }

    override fun initData() {

    }

    override fun onClick(v: View?) {

    }

    override fun setPresneter(): IPresneter {
        TODO("Not yet implemented")
    }
}