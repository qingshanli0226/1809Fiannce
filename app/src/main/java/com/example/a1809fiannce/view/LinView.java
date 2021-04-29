package com.example.a1809fiannce.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.a1809fiannce.R;

public class LinView extends LinearLayout {
    private String leftTitle;
    private String rightTitle;
    private int leftIcon;
    private int rightIcon;
    private boolean rightImgShow;
    private boolean rightTextShow;
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView leftTit;
    private TextView rightTit;
    public LinView(Context context) {
        this(context,null);
    }

    public LinView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LinView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.LinView);

        leftTitle = typedArray.getString(R.styleable.LinView_left_title);
        rightTitle = typedArray.getString(R.styleable.LinView_right_title);
        leftIcon = typedArray.getResourceId(R.styleable.LinView_left_icon, 0);
        rightIcon = typedArray.getResourceId(R.styleable.LinView_right_icon, 0);
        rightImgShow = typedArray.getBoolean(R.styleable.LinView_lin_right_show, false);
        rightTextShow = typedArray.getBoolean(R.styleable.LinView_lin_right_Text_show, false);

        typedArray.recycle();
        View view = LayoutInflater.from(context).inflate(R.layout.item_lin,this);
        leftImg = view.findViewById(R.id.lin_left_img);
        rightImg = view.findViewById(R.id.lin_right);
        leftTit = view.findViewById(R.id.lin_left_tit);
        rightTit = view.findViewById(R.id.lin_right_tit);

        leftImg.setImageResource(leftIcon);

        if (rightImgShow){
            rightImg.setImageResource(rightIcon);
        }

        leftTit.setText(leftTitle+"");

        if (rightTextShow){
            rightTit.setText(rightTitle+"");
        }


    }

}
