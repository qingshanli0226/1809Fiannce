package com.example.financial.ui.all

import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financial.R
import com.example.financial.abper.ProductAbper
import com.example.financial.base.Product
import com.example.frame_library.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_all.*
import java.security.AllPermission

class AllFramgnet :BaseFragment<AllPresenter>(),IAllCanter.View {
    private var productAbper:ProductAbper?=null
    override fun bandLayoutId(): Int = R.layout.fragment_all

    override fun initView() {
        attaPresenter(AllPresenter(AllModle(),this))
        fragment_all_recylerview.layoutManager=LinearLayoutManager(activity)
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
        if (productAbper == null) {
            productAbper= ProductAbper(list)
            fragment_all_recylerview.adapter=productAbper
        }else{
            productAbper!!.data.addAll(list)
            productAbper!!.notifyDataSetChanged()
        }

        fragment_all_recylerview.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.i("TAG", "onTouch: recyler ACTION_DOWN")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        Log.i("TAG", "onTouch: recyler ACTION_MOVE")
                    }
                }
                return true
            }

        })
    }
}