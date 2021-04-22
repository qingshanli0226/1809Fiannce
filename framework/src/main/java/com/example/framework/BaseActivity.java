package com.example.framework;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.myview.ToolBar;

public abstract   class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView, ToolBar.IToolbarListener {
    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getbandLayout());
        initView();
        initPresenter();
        initData();

        ToolBar viewById = this.findViewById(R.id.toolbar);

    }

     public void initPresenter(){};

    public void initData(){};

    public void initView() {};

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }

    @Override
    public void showError(String error) {
        Log.d("BaseActivity", error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

     public void destroy(){
        if (mPresenter!=null){
            mPresenter.destroy();
        }
     }


    @Override
    public void onLeftClick() {
        Toast.makeText(this, "左边图片", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightImgClick() {
        Toast.makeText(this, "右边图片", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightTvClick() {
        Toast.makeText(this, "右边文字", Toast.LENGTH_SHORT).show();
    }
}
