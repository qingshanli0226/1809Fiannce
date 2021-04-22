package com.example.frame_library.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.frame_library.R

class TabVIew(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): LinearLayout(context, attrs, defStyleAttr) {
    constructor(context: Context?,attrs: AttributeSet?):this(context,attrs,0)
    constructor(context: Context?):this(context,null)
    private val title:TextView by lazy {
        findViewById(R.id.view_tab_title_text)
    }

    private val left:ImageView by lazy {
        findViewById(R.id.view_tab_left_img)
    }
    private val right:ImageView by lazy {
        findViewById(R.id.view_tab_right_img)
    }

    private var textSize=30f

    init {
       var view= LayoutInflater.from(context).inflate(R.layout.view_tab, this)
            title.setTextSize(textSize)
            title.text="首页"
    }

    fun setTextSize(textSize:Float){
        this.textSize=textSize
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }
}