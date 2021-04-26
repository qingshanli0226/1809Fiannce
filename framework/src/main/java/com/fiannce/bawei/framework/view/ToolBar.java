package com.fiannce.bawei.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fiannce.bawei.framework.R;

public class ToolBar extends RelativeLayout {
    private String titleText;
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView rightTv;
    private boolean rightIsShow,leftIsShow;
    private int rightImgId,leftImgId;
    private TextView titleTv;
    private LinearLayout rightArea;
    private int titletextColor;
    private int mbackgroundColor;
    private IToolbarListener iToolbarListener;
    private RelativeLayout mRelayiveLayou;
    public ToolBar(Context context) {
        super(context);
        init(context,null,0);
    }


    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);

    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        titleText = typedArray.getString(R.styleable.ToolBar_titleText);
        titletextColor = typedArray.getColor(R.styleable.ToolBar_ToolBar_titleTextColor, Color.RED);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_leftImg,0);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_rightImage,0);
        rightIsShow = typedArray.getBoolean(R.styleable.ToolBar_rightIsShow,false);
        leftIsShow = typedArray.getBoolean(R.styleable.ToolBar_leftIsShow,false);
        mbackgroundColor = typedArray.getColor(R.styleable.ToolBar_mbackground,Color.parseColor("#142732"));
        typedArray.recycle();
        LayoutInflater from = LayoutInflater.from(context);
        from.inflate(R.layout.view_toolbar,this);
        mRelayiveLayou = findViewById(R.id.view_toolbar_RelayiveLayou);
        titleTv = findViewById(R.id.titleTv);
        rightArea = findViewById(R.id.rightArea);
        leftImg = findViewById(R.id.leftImg);
        rightImg = findViewById(R.id.rightImg);
        rightTv = findViewById(R.id.rightTv);
        titleTv.setText(titleText);
        titleTv.setTextColor(titletextColor);
        mRelayiveLayou.setBackgroundColor(mbackgroundColor);
        if (rightIsShow && rightImgId!=0) {
            rightImg.setImageResource(rightImgId);
        }
        if (leftIsShow && leftImgId!=0) {
            leftImg.setImageResource(leftImgId);
        }
    }
    //注册一个回调接口
    public void setToolbarListener(IToolbarListener iToolbarListener) {
        this.iToolbarListener = iToolbarListener;
    }
    public static interface IToolbarListener {
        void onLeftClick();
        void onRightImgClick();
        void onRightTvClick();
    }
}
