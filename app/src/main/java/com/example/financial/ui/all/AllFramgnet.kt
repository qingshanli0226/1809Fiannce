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

class AllFramgnet : BaseFragment<AllPresenter>(), IAllCanter.View {

    private var productAbper: ProductAbper? = null
    override fun bandLayoutId(): Int = R.layout.fragment_all

    override fun initView() {
        attaPresenter(AllPresenter(AllModle(), this))
        fragment_all_recylerview.layoutManager = LinearLayoutManager(activity)
    }

    override fun initData() {
        mPresenter!!.lodingData()
    }

    override fun OnLeftClickListenter() {

    }

    override fun OnTitleClickListenter() {

    }

    override fun OnRightClickListenter() {

    }

    //override fun setPresenter(): AllPresenter = AllPresenter(AllModle(),this)
    private var lastX = 0f
    override fun onLodinData(list: List<Product>) {
        if (productAbper == null) {
            productAbper = ProductAbper(list)
            fragment_all_recylerview.adapter = productAbper
        } else {
            productAbper!!.data.addAll(list)
            productAbper!!.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        productAbper = null
    }
}