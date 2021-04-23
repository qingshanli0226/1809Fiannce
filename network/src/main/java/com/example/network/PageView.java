package com.example.network;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public abstract class PageView extends FrameLayout {
    private View errorLayout;
    private View LoadLayout;
    private View SuccessView;
    private TextView ErrorText;
    public PageView(@NonNull Context context) {
        this(context,null);
    }

    public PageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        errorLayout = LayoutInflater.from(context).inflate(R.layout.item_error, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ErrorText=errorLayout.findViewById(R.id.error);
        addView(errorLayout,layoutParams);

        LoadLayout = LayoutInflater.from(context).inflate(R.layout.item_load, null);
        addView(LoadLayout,layoutParams);

        SuccessView = LayoutInflater.from(context).inflate(FindLayout(), null);
        addView(SuccessView,layoutParams);

    }

    protected abstract int FindLayout();

    public void ShowError(String str){

        ErrorText.setText(str);
        errorLayout.setVisibility(VISIBLE);
        LoadLayout.setVisibility(GONE);
        SuccessView.setVisibility(GONE);

    }

    public void ShowLoad(){

        errorLayout.setVisibility(GONE);
        LoadLayout.setVisibility(VISIBLE);
        SuccessView.setVisibility(GONE);

    }

    public void ShowSuccess(){

        errorLayout.setVisibility(GONE);
        LoadLayout.setVisibility(GONE);
        SuccessView.setVisibility(VISIBLE);

    }

}
