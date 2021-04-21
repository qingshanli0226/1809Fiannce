package com.example.frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class Basefragment<P extends Basepresenter> extends Fragment {
    protected P mPresenter;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView=inflater.inflate(Findlayout(),container,false);

    }

    protected abstract int Findlayout();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initPresenter();
        initData();
    }


    public <V extends View> V FindViewbyId(int resId){
        return mView.findViewById(resId);
    }
    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ondestroy();
    }
    public void ondestroy(){
        if (mPresenter!=null){
            mPresenter.destroy();
        }
    }
}
