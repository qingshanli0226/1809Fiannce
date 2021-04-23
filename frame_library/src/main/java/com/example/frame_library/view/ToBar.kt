package com.example.frame_library.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.frame_library.R

class ToBar(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    LinearLayout(context, attrs, defStyleAttr) ,View.OnClickListener{
    private val title: TextView by lazy {
        findViewById(R.id.view_tobar_title_text)
    }

    private val left_img: ImageView by lazy {
        findViewById(R.id.view_tobar_left_img)
    }
    private val right_img: ImageView by lazy {
        findViewById(R.id.view_tobar_right_img)
    }
    private var text: String = "标题"
    private var left_show: Boolean = true
    private var right_show: Boolean = true
    private var onClickListener:OnClickListener?=null
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_tobar, this)

        var obtainStyledAttributes = context!!.obtainStyledAttributes(attrs, R.styleable.ToBar)

        text= obtainStyledAttributes.getString(R.styleable.ToBar_title)!!

        left_show=obtainStyledAttributes.getBoolean(R.styleable.ToBar_left_show,true)!!
        right_show=obtainStyledAttributes.getBoolean(R.styleable.ToBar_right_show,true)!!

        title.text = text
        if (left_show) {
            left_img.visibility = VISIBLE
        } else {
            left_img.visibility = GONE
        }
        if (right_show) {
            right_img.visibility = VISIBLE
        } else {
            right_img.visibility = GONE
        }

        left_img.setOnClickListener(this)
        right_img.setOnClickListener(this)
        title.setOnClickListener(this)

    }

    fun setTitle(title: String) {
        this.text = text
    }

    fun setLeftShow(b:Boolean){
        left_show=b
    }

    fun setRightShow(b:Boolean){
        left_show=b
    }

    fun setonClickListener(listener: OnClickListener):ToBar{
        this.onClickListener = listener
        return this
    }

    interface OnClickListener{
       fun OnLeftClickListenter()
       fun OnTitleClickListenter()
       fun OnRightClickListenter()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.view_tobar_left_img->{ onClickListener!!.OnLeftClickListenter() }
            R.id.view_tobar_title_text->{ onClickListener!!.OnTitleClickListenter() }
            R.id.view_tobar_right_img->{ onClickListener!!.OnRightClickListenter() }
        }
    }
}