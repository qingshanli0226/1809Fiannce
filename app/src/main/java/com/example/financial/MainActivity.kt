package com.example.financial

import android.content.Context
import android.content.Intent
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.financial.abper.FragmentAbper
import com.example.financial.base.Product
import com.example.financial.base.ResultX
import com.example.financial.ui.product.ProductFragment
import com.example.financial.ulit.DataUlit
import com.example.frame_library.mvp.BaseActitvty
import com.example.frame_library.mvp.IPresneter
import com.example.net_library.evenuilt.Uilt
import com.google.android.material.tabs.TabLayout
import com.youth.banner.loader.ImageLoaderInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActitvty<IPresneter>() ,View.OnClickListener{

    override fun bandLayoutId(): Int =R.layout.activity_main

    override fun initView() {

    }

    override fun initData() {
        var arrayList = ArrayList<Fragment>()
        arrayList.add(ProductFragment())
        arrayList.add(Fragment())
        arrayList.add(Fragment())
        arrayList.add(Fragment())

        act_main_viewpage.adapter=FragmentAbper(supportFragmentManager,arrayList)

        act_main_tablayout.setupWithViewPager(act_main_viewpage)
        act_main_tablayout.getTabAt(0)!!.setText("首页")
        act_main_tablayout.getTabAt(1)!!.setText("投资")
        act_main_tablayout.getTabAt(2)!!.setText("我的资产")
        act_main_tablayout.getTabAt(3)!!.setText("更多")

        act_main_viewpage.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }
        })
    }

    override fun onClick(v: View?) {

    }

    override fun setPresneter(): IPresneter {
        TODO("Not yet implemented")
    }
}