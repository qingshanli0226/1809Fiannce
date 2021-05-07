package com.example.fiannce.fragment.mymoneyfragment;

import android.widget.TextView;
import android.widget.Toast;

import com.example.common.UserCallBack;
import com.example.fiannce.R;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.ToolBarView;
import com.example.net.mode.LogBean;

public class MyMoneyFragment extends BaseFragment implements FiannceUserManager.IUserLoginChanged {

    private ToolBarView tobView;
    private TextView user;


    public MyMoneyFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_money;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        if (UserCallBack.getInstance().getName() != null){
            user.setText(UserCallBack.getInstance().getName()+"");
        }
    }

    @Override
    protected void initView() {
        user = (TextView) mView.findViewById(R.id.user);

        FiannceUserManager.getInstance().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        FiannceUserManager.getInstance().unRegister(this);
    }

    @Override
    public void onLoginChange(LogBean isLogin) {

        if (isLogin != null){
            Toast.makeText(getContext(), "已登录", Toast.LENGTH_SHORT).show();
            user.setText(isLogin.getResult().getName());
        }else {
            Toast.makeText(getContext(), "未登录", Toast.LENGTH_SHORT).show();
        }
    }
}