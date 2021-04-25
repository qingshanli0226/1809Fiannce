package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.framework.R;

public class ProgressView extends View {
    private Paint paint;
    private final int START_ANGLE = 0;
    private final int STEP_ANGLE = 1;
    private final int RADIUS_MARGIN = 15;
    private final int FLAG_ANIMATER = 0;

    private int progressAngle = 0;
    private int percentage_angle = 0;


    private int textSize = 20;
    private int back_color=Color.GRAY;
    private int color = Color.RED;

    public int getBack_color() {
        return back_color;
    }

    public void setBack_color(int back_color) {
        this.back_color = back_color;
        invalidate();
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        invalidate();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public ProgressView(Context context) {
        this(context,null);

    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        back_color = typedArray.getColor(R.styleable.ProgressView_app_back_color, back_color);
        textSize = (int) typedArray.getDimension(R.styleable.ProgressView_app_textsize, textSize);
        color = typedArray.getColor(R.styleable.ProgressView_app_color, this.color);
        typedArray.recycle();
    }

    int w;
    int h;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        if(modeWidth == MeasureSpec.AT_MOST){
            w = 200;
        } else{
            w = MeasureSpec.getSize(widthMeasureSpec);
        }
        if(modeHeight == MeasureSpec.AT_MOST){
            h = 200;
        } else{
            h = MeasureSpec.getSize(heightMeasureSpec);

        }
        setMeasuredDimension(w,h);
    }

    public void startProgress(int percentage,boolean isStart){
        percentage_angle = (int) (percentage * 3.6);
        progressAngle = 0;
        if(isStart){
            //播放动画
            if(progressAngle <= percentage_angle){
                handler.sendEmptyMessageDelayed(FLAG_ANIMATER,5);
            } else{
                invalidate();
            }
        } else {
            //不播放视频
            progressAngle = percentage_angle;
            invalidate();

        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == FLAG_ANIMATER){
                progressAngle += STEP_ANGLE;
                invalidate();
                if(progressAngle <= percentage_angle){
                    handler.sendEmptyMessageDelayed(FLAG_ANIMATER,5);
                }
            }
        }
    };

    public void destroy(){
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画底层
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(back_color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int cx = measuredWidth/2;
        int cy = measuredHeight/2;
        int radius = (measuredWidth>measuredHeight?measuredHeight/2:measuredWidth/2)-RADIUS_MARGIN;
        canvas.drawCircle(cx,cy,radius,paint);


        //画上面
        RectF rectF = new RectF(measuredWidth/2-radius,measuredHeight/2-radius,
                measuredWidth/2+radius,measuredHeight/2+radius);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(20);
        canvas.drawArc(rectF,START_ANGLE,progressAngle,false,paint);


        //画文字

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(textSize);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        Rect rect = new Rect();
        int per = progressAngle*100/ 360;
        String percentage = per+"".split(".")[0]+"%";
        paint.getTextBounds(percentage,0,percentage.length(),rect);
        canvas.drawText(percentage,measuredWidth/2-rect.width()/2,measuredHeight/2+rect.height()/2,paint);


    }
}
