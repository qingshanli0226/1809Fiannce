package com.example.financial.abper

import android.graphics.Color
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import com.blankj.utilcode.util.SPUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.financial.R
import com.example.financial.base.Product
import com.example.frame_library.view.ProgressView

class ProductAbper(data: List<Product>?) :
    BaseQuickAdapter<Product, BaseViewHolder>(R.layout.item_product, data), View.OnTouchListener {
    private var button: Button? = null
    override fun convert(helper: BaseViewHolder?, item: Product?) {
        helper!!.setText(R.id.iteam_product_title_text, item!!.name)
        helper.setText(R.id.iteam_product_memberNum, item.memberNum)
        helper.setText(R.id.iteam_product_minTouMoney, item.minTouMoney)
        helper.setText(R.id.iteam_product_money, item.money + "万")
        helper.setText(R.id.iteam_product_suodingDays, item.suodingDays + "天")
        helper.setText(R.id.iteam_product_yearRate, item.yearRate + "%")

        var view = helper.getView<ProgressView>(R.id.iteam_product_progress)
        view.setProgress(item.progress.toInt())
        view.size = 30f
        view.textsize = 50f

        var itemView = helper.itemView
        itemView.setOnTouchListener(this)
        itemView.setTag(false)

        button = helper.getView<Button>(R.id.iteam_product_dele_but)
        button!!.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (v!!.id == R.id.iteam_product_dele_but) {
                    if (event!!.action == MotionEvent.ACTION_UP) {
                        data.remove(item)
                        notifyDataSetChanged()
                        itemView.scrollTo(0, 0)
                        itemView.setTag(false)
                    }
                }
                return false
            }
        })

    }

    private var lastX = 0f

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                if (event.rawX > 900 || v!!.getTag() as Boolean) {
                    v!!.parent.requestDisallowInterceptTouchEvent(true)
                    lastX = event.rawX
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (lastX > event.rawX) {
                    v!!.parent.requestDisallowInterceptTouchEvent(true)
                    v!!.scrollTo((lastX - event.rawX).toInt(), 0)
                } else if (v!!.getTag() as Boolean && lastX < event.rawX) {
                    v!!.parent.requestDisallowInterceptTouchEvent(true)
                    v!!.scrollTo((button!!.width + (lastX - event.rawX)).toInt(), 0)
                } else {
                    v!!.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            MotionEvent.ACTION_UP -> {
                if (lastX - event.rawX > button!!.width / 2 || v!!.getTag() as Boolean && event.rawX - lastX < button!!.width / 2) {
                    v!!.scrollTo(button!!.width, 0)
                    v.setTag(true)
                } else if (lastX - event.rawX < button!!.width / 2 || v!!.getTag() as Boolean && event.rawX - lastX > button!!.width / 2) {
                    v!!.scrollTo(0, 0)
                    v.setTag(false)
                }
                lastX = 0f
            }
        }
        return false
    }

}