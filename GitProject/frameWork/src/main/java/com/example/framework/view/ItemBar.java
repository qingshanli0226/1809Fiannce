package com.example.framework.view;

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

import androidx.annotation.Nullable;

import com.example.framework.R;

public class ItemBar extends LinearLayout {
    public ItemBar(Context context) {
        this(context,null);
    }

    public ItemBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ItemBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }
    private String text,rightText;
    private int textColor = Color.RED, backColor = Color.GRAY,textRightColor = Color.RED;
    private int leftSrc, rightSrc;
    private boolean leftIsshow=false, rightIsShow=false,rightTextIsShow=false;
    private ItemOnClickLisenter itemOnClickLisenter;
    private int textSize,rightTextSize;
    public void setItemOnClickLisenter(ItemOnClickLisenter itemOnClickLisenter) {
        this.itemOnClickLisenter = itemOnClickLisenter;
    }
    private ImageView leftItemPic;
    private ImageView rightItemPic;
    private TextView centerItemTitle;
    private RelativeLayout reItem;
    private TextView rightItemTitle;


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.set_item_layout, this);
        leftItemPic = inflate.findViewById(R.id.left_item_pic);
        rightItemPic = inflate.findViewById(R.id.right_item_pic);
        centerItemTitle = inflate.findViewById(R.id.center_item_title);
        rightItemTitle = inflate.findViewById(R.id.right_item_title);
        reItem = inflate.findViewById(R.id.re_item);


        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemBar);
            text = (String) typedArray.getText(R.styleable.ItemBar_app_item_text);
            textSize = (int) typedArray.getDimension(R.styleable.ItemBar_app_item_text_size, textSize);
            textColor = typedArray.getColor(R.styleable.ItemBar_app_item_text_color, textColor);

            rightText = (String) typedArray.getText(R.styleable.ItemBar_app_item_right_text);
            rightTextSize = (int) typedArray.getDimension(R.styleable.ItemBar_app_item_right_text_size, rightTextSize);
            textRightColor = typedArray.getColor(R.styleable.ItemBar_app_item_right_text_color, textRightColor);
            rightTextIsShow = typedArray.getBoolean(R.styleable.ItemBar_app_item_right_text_isshow, rightTextIsShow);

            backColor = typedArray.getColor(R.styleable.ItemBar_app_item_back_color, backColor);
            leftSrc = typedArray.getResourceId(R.styleable.ItemBar_app_item_left_src, 0);
            rightSrc = typedArray.getResourceId(R.styleable.ItemBar_app_item_right_src, 0);
            leftIsshow = typedArray.getBoolean(R.styleable.ItemBar_app_item_left_isshow, leftIsshow);
            rightIsShow = typedArray.getBoolean(R.styleable.ItemBar_app_item_right_isshow, rightIsShow);
            typedArray.recycle();
        }

        centerItemTitle.setText(text);
        centerItemTitle.setTextColor(textColor);
        centerItemTitle.setTextSize(textSize);
        reItem.setBackgroundColor(backColor);


        rightItemTitle.setText(rightText);
        rightItemTitle.setTextColor(textRightColor);
        rightItemTitle.setTextSize(rightTextSize);
        if(rightTextIsShow){
            rightItemTitle.setVisibility(VISIBLE);
        } else{
            rightItemTitle.setVisibility(GONE);
        }
        if (leftSrc != 0 && leftIsshow) {
            leftItemPic.setImageResource(leftSrc);
        }
        if (rightSrc != 0 && rightIsShow) {
            rightItemPic.setImageResource(rightSrc);
        }

        leftItemPic.setOnClickListener(v -> {
            if (itemOnClickLisenter != null) {
                itemOnClickLisenter.leftOnClick();
            }
        });
        rightItemPic.setOnClickListener(v -> {
            if (itemOnClickLisenter != null) {
                itemOnClickLisenter.rightOnClick();
            }
        });
        centerItemTitle.setOnClickListener(v -> {
            if (itemOnClickLisenter != null) {
                itemOnClickLisenter.titleOnClick();
            }
        });

        reItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnClickLisenter != null) {
                    itemOnClickLisenter.itemOnClick();
                }
            }
        });

        rightItemTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnClickLisenter != null) {
                    itemOnClickLisenter.rightTextOnClick();
                }
            }
        });
    }

    public void setItemRightText(String text) {
        if (rightItemTitle != null) {
            rightItemTitle.setText(text);
        }
    }

    public void setItemRightTextColor(int color) {
        if (rightItemTitle != null) {
            rightItemTitle.setTextColor(color);
        }
    }
    public void setItemRightTextSize(int size) {
        if (rightItemTitle != null) {
            rightItemTitle.setTextSize(size);
        }
    }

    public void setRightTextIsshow(boolean isShow){
        if (rightItemTitle != null) {
            if (isShow) {
                rightItemTitle.setVisibility(View.VISIBLE);
            } else {
                rightItemTitle.setVisibility(View.GONE);
            }
        }
    }

    public void setItemText(String text) {
        if (centerItemTitle != null) {
            centerItemTitle.setText(text);
        }
    }

    public void setItemTextColor(int color) {
        if (centerItemTitle != null) {
            centerItemTitle.setTextColor(color);
        }
    }
    public void setItemTextSize(int size) {
        if (centerItemTitle != null) {
            centerItemTitle.setTextSize(size);
        }
    }
    public void setItemBackgroundColor(int color){
        if (reItem != null) {
            reItem.setBackgroundColor(color);
        }
    }

    public void setLeftSrc(int src){
        if (leftItemPic != null) {
            leftItemPic.setImageResource(src);
        }

    }
    public void setRightSrc(int src){
        if (rightItemPic != null) {
            rightItemPic.setImageResource(src);

        }
    }
    public void setleftIsshow(boolean isShow){
        if (leftItemPic != null) {
            if (isShow) {
                leftItemPic.setVisibility(View.VISIBLE);
            } else {
                leftItemPic.setVisibility(View.GONE);

            }
        }
    }
    public void setRightIsshow(boolean isShow){
        if (rightItemPic != null) {
            if (isShow) {
                rightItemPic.setVisibility(View.VISIBLE);
            } else {
                rightItemPic.setVisibility(View.GONE);

            }
        }
    }

    public static interface ItemOnClickLisenter{
        void leftOnClick();
        void rightOnClick();
        void titleOnClick();
        void rightTextOnClick();
        void itemOnClick();
    }

}
