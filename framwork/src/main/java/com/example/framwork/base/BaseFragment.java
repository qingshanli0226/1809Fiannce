package com.example.framwork.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import com.example.framwork.R;
import com.example.framwork.call.FiannceNetManager;
import com.example.framwork.view.PageView;
import com.example.framwork.view.TobView;


public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements FiannceNetManager.NetConnectListener {
    protected View baseView;
    protected P mPresenter;
    protected PageView pageView;
    protected TobView tobView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        baseView = pageView =new PageView(getContext()) {
            @Override
            protected int FindLayout() {
                return FindLayout1();
            }
        };
        tobView = baseView.findViewById(R.id.tob);
        return baseView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        FiannceNetManager.getInstance().RegisterConnect(this);
    }


    public <V extends View> V FindByID(int id) {
        return baseView.findViewById(id);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int FindLayout1();


    @Override
    public void OnConnect() {
    }

    @Override
    public void DisConnect() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FiannceNetManager.getInstance().UnRegisterConnect(this);
    }
}
