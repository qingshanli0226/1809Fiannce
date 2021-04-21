package com.example.frame_library.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

class ProgressView:View {
    private val paint:Paint= Paint()

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){init(context,attrs,defStyleAttr)}

    /***
     * 初始化函数
     */
    private fun init(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {

    }

    private var progress=0f

    /**
     * @param progress 圆的进度
     * @param b 是否展示动画 true 为展示动画 flase 不展示
     */
    fun setProgress(progress:Float,b:Boolean){
        this.progress=progress;
        if (b) {
            Animation()
        }else{
            invalidate()
        }
    }

    /***
     * 加载动画
     */
    protected fun Animation(){
        var animator = ValueAnimator.ofFloat(0f, progress)
        animator.addUpdateListener {
            var t = it.animatedValue as Float

            this.progress=t
            invalidate()
        }
        animator.duration=5*1000
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.setColor(Color.GRAY)
        paint.strokeWidth=50f
        paint.style=Paint.Style.STROKE
        paint.isAntiAlias=true

        var mWidth = measuredWidth
        var mHeight = measuredHeight
        var viewY = mHeight / 2
        var viewX = mWidth / 2
        var radius=if (mWidth>mHeight){mHeight/2}else{mWidth/2}-paint.strokeWidth/2

        canvas!!.drawCircle(viewX.toFloat(),viewY.toFloat(),radius,paint)

        paint.setColor(Color.RED)

        canvas!!.drawArc(viewX-radius,viewY-radius,viewX+radius,viewY+radius,0f,(progress*3.6).toFloat(),false,paint)

        paint.color=Color.BLACK
        paint.textSize=100f
        paint.style=Paint.Style.FILL

        var rect = Rect()
        var text = "${progress.toInt()}%"
        paint.getTextBounds(text,0,text.length,rect)
        canvas.drawText(text,(viewX-rect.width()/2).toFloat(),(viewY+rect.height()/2).toFloat(),paint)
    }

}