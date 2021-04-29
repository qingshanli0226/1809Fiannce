package com.example.framework.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.framework.R;

public class MoreItemPage extends FrameLayout {
    private ImageView moreItemLeftImage;
    private TextView moreItemLeftText;
    private ImageView moreItemRightImage;
    private TextView moreItemRightText;

    private String leftText;
    private String rightText;
    private int leftImage;//左图内容
    private boolean leftImageIsShow;//左图是否显示
    private int rightImage;//有图内容
    private boolean rightImageIsShow;//右图是否显示

    public MoreItemPage(@NonNull Context context) {
        this(context, null);
    }

    public MoreItemPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoreItemPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MoreItem);
        leftText = typedArray.getString(R.styleable.MoreItem_moreLeftText);
        rightText = typedArray.getString(R.styleable.MoreItem_moreRightText);
        leftImage = typedArray.getResourceId(R.styleable.MoreItem_moreLeftImage, 0);
        leftImageIsShow = typedArray.getBoolean(R.styleable.MoreItem_moreLeftIsShow, false);
        rightImage = typedArray.getResourceId(R.styleable.MoreItem_moreRightImage, 0);
        rightImageIsShow = typedArray.getBoolean(R.styleable.MoreItem_moreRightIsShow, false);
        typedArray.recycle();
        //获取控件
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_more_item, this);
        moreItemLeftImage= inflate.findViewById(R.id.moreItemLeftImage);
        moreItemLeftText= inflate.findViewById(R.id.moreItemLeftText);
        moreItemRightImage= inflate.findViewById(R.id.moreItemRightImage);
        moreItemRightText= inflate.findViewById(R.id.moreItemRightText);

        //通过数据渲染
        moreItemLeftText.setText(leftText);
        moreItemRightText.setText(rightText);
        //判断是否显示图片
        if (moreItemLeftImage!=null&&leftImage!=0){
            moreItemLeftImage.setImageResource(leftImage);
        }
        if (moreItemRightImage!=null&&rightImage!=0){
            moreItemRightImage.setImageResource(rightImage);
        }

    }
}
