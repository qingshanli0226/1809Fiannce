package com.example.framwork.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framwork.R;


public class TobView extends RelativeLayout {
    private boolean rightShow;
    private boolean leftShow;
    private String text;
    private int textColor;
    private int leftImgGroup;
    private int rightImgGroup;
    private ImageView leftImg;
    private TextView tobTit;
    private ImageView rightImg;
    private RelativeLayout tob;
    private int background;
    private iImgCallBack imgCallBack;
    public TobView(Context context) {
        this(context,null);
    }

    public TobView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TobView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TobView);
        rightShow = typedArray.getBoolean(R.styleable.TobView_right_show, false);
        leftShow = typedArray.getBoolean(R.styleable.TobView_left_show, false);
        text = typedArray.getString(R.styleable.TobView_text);
        textColor = typedArray.getColor(R.styleable.TobView_textColor, Color.BLACK);
        leftImgGroup = typedArray.getResourceId(R.styleable.TobView_left_img, 0);
        rightImgGroup = typedArray.getResourceId(R.styleable.TobView_right_img, 0);
        background = typedArray.getColor(R.styleable.TobView_backgroundColor, Color.WHITE);
        typedArray.recycle();


        View inflate = LayoutInflater.from(context).inflate(R.layout.item_tob, this);
        leftImg = inflate.findViewById(R.id.left_img);
        tobTit = inflate.findViewById(R.id.tob_tit);
        rightImg = inflate.findViewById(R.id.right_img);
        tob=inflate.findViewById(R.id.tob);
        if (rightShow){
            rightImg.setImageResource(rightImgGroup);
        }
        if (leftShow){
            leftImg.setImageResource(leftImgGroup);
        }

        tobTit.setText(text);
        tobTit.setTextColor(textColor);

        tob.setBackgroundColor(background);

        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgCallBack!=null){

                    imgCallBack.OnRightImgListener();

                }
            }
        });

        leftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                imgCallBack.OnLeftImgListener();

            }
        });


    }

    public void setTobTit(String tit){
        tobTit.setText(tit+"");
        invalidate();
    }


    public void setImgCallBackListener(iImgCallBack imgCallBack){
        this.imgCallBack = imgCallBack;

    }
    public static interface iImgCallBack{
        void OnLeftImgListener();
        void OnRightImgListener();
    }
}
