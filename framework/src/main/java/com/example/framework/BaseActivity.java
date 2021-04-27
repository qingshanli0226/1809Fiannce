package com.example.framework;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.myview.LoadingPage;
import com.example.framework.myview.ToolBar;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView, ToolBar.IToolbarListener {
    protected P mPresenter;
    private ToolBar toolBar;
    protected boolean isUserLoading=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage=new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getbandLayout();
            }
        };
        setContentView(loadingPage);
        initView();
        initPresenter();
        initData();
    }


    protected LoadingPage loadingPage;
    protected abstract int getbandLayout();
    protected abstract  void initView() ;
    protected abstract  void initData();
    protected  abstract void initPresenter();

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
        if (loadingPage!=null){
            loadingPage.clearAnimation();
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
