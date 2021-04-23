package com.finance.framework.view;

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

import com.finance.framework.R;

public abstract class LoadingPage extends FrameLayout {
    public LoadingPage(@NonNull Context context) {
        super(context);
        init(context,null,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,0);
    }

    private View errorView;
    private TextView errorTv;
    private View successView;
    private View loadingView;

    public void init(Context context,AttributeSet attrs,int i){
        LayoutInflater inflater = LayoutInflater.from(context);
        errorView = inflater.inflate(R.layout.view_error, null);
        errorTv = errorView.findViewById(R.id.errorTv);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(errorView,layoutParams);
        successView = inflater.inflate(getSuccessLayoutId(),null);
        addView(successView,layoutParams);
        loadingView = inflater.inflate(R.layout.view_loading, null);
        addView(loadingView,layoutParams);
        showSuccessView();
    }

    public void showSuccessView(){
        errorView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
    }

    public void showErrorView(){
        errorView.setVisibility(VISIBLE);
        successView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
    }

    public void showLoadingView(){
        errorView.setVisibility(GONE);
        successView.setVisibility(GONE);
        loadingView.setBackgroundColor(Color.WHITE);
        loadingView.setVisibility(VISIBLE);
    }

    public void showTransparentLoadingView(){
        errorView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        loadingView.setBackgroundColor(Color.TRANSPARENT);
        loadingView.setVisibility(VISIBLE);
    }

    public void showError(String errorInfo){
        showErrorView();
        errorTv.setText(errorInfo);
    }


    protected abstract int getSuccessLayoutId();
}
