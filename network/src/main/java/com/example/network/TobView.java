package com.example.network;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TobView extends RelativeLayout {
    private boolean right_show;
    private boolean left_show;
    private String text;
    private int textColor;
    private int left_img;
    private int right_img;
    private ImageView leftImg;
    private TextView tobTit;
    private ImageView rightImg;
    private LinearLayout tob;
    private int background;
    private ImgCallBack imgCallBack;
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

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TobView);
        right_show = typedArray.getBoolean(R.styleable.TobView_right_show, false);
        left_show = typedArray.getBoolean(R.styleable.TobView_left_show, false);
        text = typedArray.getString(R.styleable.TobView_text);
        textColor = typedArray.getColor(R.styleable.TobView_textColor, Color.BLACK);
        left_img = typedArray.getResourceId(R.styleable.TobView_left_img, 0);
        right_img = typedArray.getResourceId(R.styleable.TobView_right_img, 0);
        background = typedArray.getColor(R.styleable.TobView_backgroundColor, Color.WHITE);
        typedArray.recycle();


        View inflate = LayoutInflater.from(context).inflate(R.layout.item_tob, this);
        leftImg = inflate.findViewById(R.id.left_img);
        tobTit = inflate.findViewById(R.id.tob_tit);
        rightImg = inflate.findViewById(R.id.right_img);
        tob = inflate.findViewById(R.id.tob);

        if (right_show){
            rightImg.setImageResource(right_img);
        }
        if (left_show){
            leftImg.setImageResource(left_img);
        }

        tobTit.setText(text);

        tobTit.setTextColor(textColor);

        tob.setBackgroundColor(background);

        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgCallBack!=null){

                    imgCallBack.OnRightImg();
                }
            }
        });


    }
    public void ImgCallBackListener(ImgCallBack imgCallBack){
        this.imgCallBack = imgCallBack;

    }
    public static interface ImgCallBack{
        void OnLeftImg();
        void OnRightImg();
    }
}
