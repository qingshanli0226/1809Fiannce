package com.fiannce.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.blankj.utilcode.util.FlashlightUtils.destroy;

public abstract class BaseFragment<T extends BasePresenter>  extends Fragment {

    protected T httpPresenter;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayout(),container,false);
        initView();
        initPresenter();
        initData();
        return mView;
    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

   public void destroy(){
        if (httpPresenter != null){
            httpPresenter.detachView();
        }
   }

}
