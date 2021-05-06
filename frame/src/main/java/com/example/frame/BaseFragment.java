package com.example.frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements CacheUserManager.ILoginChange{
    protected P mPresenter;
    protected LoadingPage loadingPage;
    protected MyToolbar myToolbar;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       mView= loadingPage=new LoadingPage(getActivity()) {
            @Override
            protected int getSuccessLayoutId() {
                return Findlayout();
            }
        };
     return mView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initPresenter();
       CacheUserManager.getInstance().registerLogin(this);
    }


    public <V extends View> V findViewById(int resId){
        return mView.findViewById(resId);
    }

    protected abstract void initPresenter();

    protected abstract void initView();
    protected abstract int Findlayout();

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ondestroy();
        CacheUserManager.getInstance().unregisterLogin(this);
    }
    public void ondestroy(){
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }
}
