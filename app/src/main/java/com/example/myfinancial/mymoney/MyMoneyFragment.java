package com.example.myfinancial.mymoney;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.framework.BaseFragment;
import com.example.framework.mannager.FiannceUserMannager;
import com.example.myfinancial.R;

public class MyMoneyFragment extends BaseFragment {

    private ImageView headPortrait;
    private TextView loginName;

    @Override
    public int getbandLayout() {
        return R.layout.fragment_my_money;
    }

    @Override
    public void initView() {
        headPortrait = (ImageView) findViewById(R.id.headPortrait);
        loginName = (TextView) findViewById(R.id.loginName);

        //注册登陆状态
        FiannceUserMannager.getInstance().register(this);
    }

    @Override
    public void initData() {
        //判断是否登陆
        if (FiannceUserMannager.getInstance().isLogin()){
            Toast.makeText(getActivity(), "登录了", Toast.LENGTH_SHORT).show();
            Glide.with(getActivity()).load(R.drawable.my_user_bg_icon).into(headPortrait);
            loginName.setText("已登录");
        }else {
            Toast.makeText(getActivity(), "没有登录", Toast.LENGTH_SHORT).show();
            Glide.with(getActivity()).load(R.drawable.my_user_default).into(headPortrait);
            loginName.setText("未登录");
        }

    }

    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void initPresenter() {

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

    @Override
    public void onLoginChanged(boolean isLogin) {
        if (isLogin){
            loginName.setText("已登录");
            Glide.with(getActivity()).load(R.drawable.my_user_bg_icon).into(headPortrait);
        }else {
            loginName.setText("未登录");
            Glide.with(getActivity()).load(R.drawable.my_user_default).into(headPortrait);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
        FiannceUserMannager.getInstance().unRegister(this);
    }
}