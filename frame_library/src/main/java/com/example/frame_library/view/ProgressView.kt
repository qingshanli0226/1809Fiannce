package com.example.frame_library.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.TextView
import com.example.frame_library.R

class ProgressView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int):View(context, attrs, defStyleAttr) {

    private val paint:Paint= Paint()
    private var textcolor:Int?=null
    private var Star:Boolean?=null
    private var progress:Int?=null
    private var animator:ValueAnimator?=null
    private var color:Int=Color.RED
    var textsize:Float=80f
    var size:Float=50f
    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    init {
        var obtainStyledAttributes =
            context!!.obtainStyledAttributes(attrs, R.styleable.ProgressView)
        textcolor=obtainStyledAttributes.getColor(R.styleable.ProgressView_textColor,Color.BLACK)
        Star=obtainStyledAttributes.getBoolean(R.styleable.ProgressView_star,true)
        progress=obtainStyledAttributes.getInt(R.styleable.ProgressView_progress,30)
        color=obtainStyledAttributes.getColor(R.styleable.ProgressView_color,Color.RED)
        textsize=obtainStyledAttributes.getInt(R.styleable.ProgressView_textSize,80).toFloat()
        size=obtainStyledAttributes.getInt(R.styleable.ProgressView_size,50).toFloat()
        obtainStyledAttributes.recycle()
    }

    /**
     * @param progress 圆的进度
     */
    fun setProgress(progress:Int){
        this.progress=progress;
        invalidate()
    }

    fun setStar(b:Boolean){
        this.Star=b
        invalidate()
    }

    /***
     * 加载动画
     */
     fun Animation(){
        if (Star!!) {
            animator = ValueAnimator.ofFloat(0f, progress!!.toFloat())
            animator!!.addUpdateListener {
                Star=false
                var t = it.animatedValue as Float

                this.progress=t.toInt()
                invalidate()
            }
            animator!!.duration=3*1000
            animator!!.start()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var wMode = MeasureSpec.getMode(widthMeasureSpec)
        var hMode = MeasureSpec.getMode(heightMeasureSpec)
        var wSize=if (wMode==MeasureSpec.AT_MOST){
            500
        }else{
            MeasureSpec.getSize(widthMeasureSpec)
        }
        var hSize=if (hMode==MeasureSpec.AT_MOST){
            500
        }else{
            MeasureSpec.getSize(heightMeasureSpec)
        }
        setMeasuredDimension(wSize,hSize)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.setColor(Color.GRAY)
        paint.strokeWidth=size
        paint.style=Paint.Style.STROKE
        paint.isAntiAlias=true

        var mWidth = measuredWidth
        var mHeight = measuredHeight

        var viewY = mHeight / 2
        var viewX = mWidth / 2
        var radius=if (mWidth>mHeight){mHeight/2}else{mWidth/2}-paint.strokeWidth/2

        canvas!!.drawCircle(viewX.toFloat(),viewY.toFloat(),radius,paint)

        paint.setColor(color)

        canvas!!.drawArc(viewX-radius,viewY-radius,viewX+radius,viewY+radius,0f,(progress!!*3.6).toFloat(),false,paint)

        paint.color= textcolor!!
        paint.textSize=textsize
        paint.style=Paint.Style.FILL

        var rect = Rect()
        var text = "${progress}%"
        paint.getTextBounds(text,0,text.length,rect)
        canvas.drawText(text,(viewX-rect.width()/2).toFloat(),(viewY+rect.height()/2).toFloat(),paint)

        Animation()
    }

    fun destroy(){
        animator!!.cancel();
        animator=null
    }
}