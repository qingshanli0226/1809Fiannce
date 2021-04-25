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
    private String text;
    private int text_color = Color.RED, back_color = Color.GRAY;
    private int left_src, right_src;
    private boolean left_isshow, right_ishow;
    private ItemOnClickLisenter itemOnClickLisenter;

    public void setItemOnClickLisenter(ItemOnClickLisenter itemOnClickLisenter) {
        this.itemOnClickLisenter = itemOnClickLisenter;
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.set_item_layout, this);
        ImageView left_item_pic = inflate.findViewById(R.id.left_item_pic);
        ImageView right_item_pic = inflate.findViewById(R.id.right_item_pic);
        TextView center_item_title = inflate.findViewById(R.id.center_item_title);
        RelativeLayout re_item = inflate.findViewById(R.id.re_item);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemBar);
            text = (String) typedArray.getText(R.styleable.ItemBar_app_item_text);
            text_color = typedArray.getColor(R.styleable.ItemBar_app_item_text_color, text_color);
            back_color = typedArray.getColor(R.styleable.ItemBar_app_item_back_color, back_color);
            left_src = typedArray.getResourceId(R.styleable.ItemBar_app_item_left_src, 0);
            right_src = typedArray.getResourceId(R.styleable.ItemBar_app_item_right_src, 0);
            left_isshow = typedArray.getBoolean(R.styleable.ItemBar_app_item_left_isshow, left_isshow);
            right_ishow = typedArray.getBoolean(R.styleable.ItemBar_app_item_right_isshow, right_ishow);
            typedArray.recycle();
        }

        center_item_title.setText(text);
        center_item_title.setTextColor(text_color);
        re_item.setBackgroundColor(back_color);

        if (left_src != 0 && left_isshow) {
            left_item_pic.setImageResource(left_src);
        }
        if (right_src != 0 && right_ishow) {
            right_item_pic.setImageResource(right_src);
        }

        left_item_pic.setOnClickListener(v -> {
            if (itemOnClickLisenter != null) {
                itemOnClickLisenter.leftOnClick();
            }
        });
        right_item_pic.setOnClickListener(v -> {
            if (itemOnClickLisenter != null) {
                itemOnClickLisenter.rightOnClick();
            }
        });
        center_item_title.setOnClickListener(v -> {
            if (itemOnClickLisenter != null) {
                itemOnClickLisenter.titleOnClick();
            }
        });

        re_item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnClickLisenter != null) {
                    itemOnClickLisenter.itemOnClick();
                }
            }
        });
    }

    public static interface ItemOnClickLisenter{
        void leftOnClick();
        void rightOnClick();
        void titleOnClick();
        void itemOnClick();
    }

}
