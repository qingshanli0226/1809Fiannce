package com.example.frame_library.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.example.frame_library.R

abstract class LodingPage(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    FrameLayout(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context!!, null)

    protected var textView: TextView? = null
    protected var ErrorView: View? = null
    protected var LodingView: View? = null
    protected var SuccessLayout: View? = null

    init {
        var view = LayoutInflater.from(context)
        var layoutParams =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        ErrorView = view.inflate(R.layout.view_error, null)
        textView = ErrorView!!.findViewById(R.id.view_error_text)
        addView(ErrorView, layoutParams)

        LodingView = view.inflate(R.layout.view_lodin, null)
        addView(LodingView, layoutParams)

        SuccessLayout = view.inflate(getSuccessLayoutId(), null)
        addView(SuccessLayout, layoutParams)

        showSuccessLayout()
    }

    abstract fun getSuccessLayoutId(): Int

    fun showLodingView() {
        LodingView!!.visibility = VISIBLE
        ErrorView!!.visibility = GONE
        SuccessLayout!!.visibility = GONE
        LodingView!!.setBackgroundColor(Color.WHITE)
    }

    fun showSuccessLayout() {
        LodingView!!.visibility = GONE
        ErrorView!!.visibility = GONE
        SuccessLayout!!.visibility = VISIBLE
    }

    fun showTransparentLoadingView() {
        LodingView!!.visibility = VISIBLE
        ErrorView!!.visibility = GONE
        SuccessLayout!!.visibility = GONE
        LodingView!!.setBackgroundColor(Color.TRANSPARENT)
    }

    fun showErrorView() {
        LodingView!!.visibility = GONE
        ErrorView!!.visibility = VISIBLE
        SuccessLayout!!.visibility = GONE
    }

    fun showError(text: String) {
        showErrorView()
        this.textView!!.text = text
    }
}