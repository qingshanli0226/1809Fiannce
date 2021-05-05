package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.manager.FiannceNetManager;
import com.example.net.PageView;
import com.example.net.ToolBarView;

public abstract class BaseActivity<T extends BasePresenter>extends AppCompatActivity implements FiannceNetManager.NetConnectListener{

    protected T httpPresenter;
    protected PageView pageView;
    protected ToolBarView tobView;

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
        FiannceNetManager.getInstance().RegisterConnect(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();

        FiannceNetManager.getInstance().UnRegisterConnect(this);
    }

    public void destroy(){
        if (httpPresenter != null){
            httpPresenter.detachView();
        }
    }

    @Override
    public void onConnect() {

    }

    @Override
    public void DisConnect() {

    }
}
