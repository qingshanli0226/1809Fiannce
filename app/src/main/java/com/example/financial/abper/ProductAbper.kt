package com.example.financial.abper

import android.graphics.Color
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.financial.R
import com.example.financial.base.Product
import com.example.frame_library.view.ProgressView

class ProductAbper(data: List<Product>?) :
    BaseQuickAdapter<Product, BaseViewHolder>(R.layout.item_product, data) , View.OnTouchListener{
    override fun convert(helper: BaseViewHolder?, item: Product?) {
        helper!!.setText(R.id.iteam_product_title_text, item!!.name)
        helper!!.setText(R.id.iteam_product_memberNum, item!!.memberNum)
        helper!!.setText(R.id.iteam_product_minTouMoney, item!!.minTouMoney)
        helper!!.setText(R.id.iteam_product_money, item!!.money + "万")
        helper!!.setText(R.id.iteam_product_suodingDays, item!!.suodingDays + "天")
        helper!!.setText(R.id.iteam_product_yearRate, item!!.yearRate + "%")
        var view = helper.getView<ProgressView>(R.id.iteam_product_progress)
        view.setProgress(item.progress.toInt())
        view.size = 30f
        view.textsize = 50f

        var itemView = helper.itemView
        //itemView.setBackgroundColor(Color.RED)
        itemView.setOnTouchListener(this)

    }

    private var lastX=0f

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.getRawX()
                Log.i("TAG", "onTouch: ${event.getRawX()} ACTION_DOWN")

            }
            MotionEvent.ACTION_MOVE -> {
                Log.i("TAG", "onTouch: ${event.getRawX()} ACTION_MOVE")
            }
        }
        return true
    }


}