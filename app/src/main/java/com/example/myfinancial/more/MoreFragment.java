package com.example.myfinancial.more;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseFragment;
import com.example.framework.myview.MoreItemPage;
import com.example.myfinancial.R;

public class MoreFragment extends BaseFragment {

    private MoreItemPage userRegister;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //用户注册点击
        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mare/register").navigation();
            }
        });
    }

    @Override
    public void initView() {
        userRegister = (MoreItemPage) findViewById(R.id.userRegister);
    }

    @Override
    public int getbandLayout() {
        return R.layout.fragment_more;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}