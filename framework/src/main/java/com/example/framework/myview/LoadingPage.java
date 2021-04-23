package com.example.framework.myview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.framework.R;

public abstract class LoadingPage extends FrameLayout {
    private View loadingView;
    private View successView;
    private View errorView;
    private TextView errorTv;


    public LoadingPage(@NonNull Context context) {
        this(context,null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //获取errorView 和 errorTv
        errorView = inflater.inflate(R.layout.view_error, null);
        errorTv  = errorView.findViewById(R.id.errorTv);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(errorView,layoutParams);
        //获取successview
        successView = inflater.inflate(getSuccessLayoutId(), null);
        addView(successView,layoutParams);
        //获取loadingview
         loadingView = inflater.inflate(R.layout.view_loading, null);
         addView(loadingView,layoutParams);
        showSuccessView();


    }
    protected abstract int getSuccessLayoutId();

    public void showLoadingView(){
        loadingView.setBackgroundColor(Color.WHITE);
        errorView.setVisibility(GONE);
        successView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
    }
    public void showErrorView(){
        errorView.setVisibility(VISIBLE);
        successView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
    }
    public void showSuccessView(){
        errorView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
    }

    public void showTransparentLoadingView(){
        errorView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        loadingView.setVisibility(VISIBLE);
        loadingView.setBackgroundColor(Color.TRANSPARENT);
    }
    public void showError(String errorInfo){
       showErrorView();
       errorTv.setText(errorInfo);
    }
}
