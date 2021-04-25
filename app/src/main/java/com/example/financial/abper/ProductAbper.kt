package com.example.financial.abper

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.financial.R
import com.example.financial.base.Product

class ProductAbper(data: MutableList<Product>?) :
    BaseQuickAdapter<Product, BaseViewHolder>(R.layout.item_product, data) {
    override fun convert(helper: BaseViewHolder?, item: Product?) {

    }
}