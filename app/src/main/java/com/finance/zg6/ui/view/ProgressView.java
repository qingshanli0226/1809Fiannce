package com.finance.zg6.ui.view;

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

import com.finance.zg.R;

public class ProgressView extends View {
    private Paint paint;
    private final int START_ANGLE = 0;
    private final int STEP_ANGLE = 1;
    private final int RADIUS_MARGIN = 15;
    private final int FLAG_ANIMATER = 0;

    private int progressAngle = 0;
    private int percentage_angle = 0;

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
    int color = Color.RED;
    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
//        typedArray.getColor(R.styleable.ProgressView_all_finance)

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画底层
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
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
        paint.setTextSize(40);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        Rect rect = new Rect();
        int per = progressAngle*100/ 360;
        String percentage = per+"".split(".")[0]+"%";
        paint.getTextBounds(percentage,0,percentage.length(),rect);
        canvas.drawText(percentage,measuredWidth/2-rect.width()/2,measuredHeight/2+rect.height()/2,paint);


    }
}
