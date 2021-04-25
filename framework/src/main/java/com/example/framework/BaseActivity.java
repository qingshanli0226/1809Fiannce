package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.net.PageView;
import com.example.net.TobView;

public abstract class BaseActivity<T extends BasePresenter>extends AppCompatActivity {

    protected T httpPresenter;
    protected PageView pageView;
    protected TobView tobView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageView = new PageView(this) {
            @Override
            protected int FindLayout() {
                return getLayoutId();
            }
        };

        setContentView(getLayoutId());
        tobView = pageView.findViewById(R.id.tob);
        initView();
        initPresenter();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy(){
        if (httpPresenter != null){
            httpPresenter.detachView();
        }
    }
}
