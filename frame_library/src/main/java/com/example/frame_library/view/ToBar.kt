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

    private val leftImg: ImageView by lazy {
        findViewById(R.id.view_tobar_left_img)
    }
    private val rightImg: ImageView by lazy {
        findViewById(R.id.view_tobar_right_img)
    }

    private var onClickListener:OnClickListener?=null
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_tobar, this)

        var obtainStyledAttributes = context!!.obtainStyledAttributes(attrs, R.styleable.ToBar)

        var title = obtainStyledAttributes.getString(R.styleable.ToBar_title)!!

        var lefshow = obtainStyledAttributes.getBoolean(R.styleable.ToBar_left_show, true)!!
        var rightshow = obtainStyledAttributes.getBoolean(R.styleable.ToBar_right_show, true)!!

        setTitle(title)
        setLeftShow(lefshow)
        setRightShow(rightshow)

        leftImg.setOnClickListener(this)
        rightImg.setOnClickListener(this)
        this.title.setOnClickListener(this)

    }

    fun setTitle(title: String) {
        this.title.text = title
    }

    fun setLeftShow(b:Boolean){
        if (b) {
            leftImg.visibility = VISIBLE
        } else {
            leftImg.visibility = GONE
        }
    }

    fun setRightShow(b:Boolean){
        if (b) {
            rightImg.visibility = VISIBLE
        } else {
            rightImg.visibility = GONE
        }
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