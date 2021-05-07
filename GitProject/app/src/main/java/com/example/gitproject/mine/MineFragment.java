package com.example.gitproject.mine;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.CommonConstant;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.framework.view.ToolBar;
import com.example.gitproject.R;
import com.example.net.bean.LoginBean;


public class MineFragment extends BaseFragment implements CacheUserManager.ILoginChange {



    private ImageView mineHead;
    private TextView mineName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {


        mineHead = (ImageView) findViewById(R.id.mine_head);
        mineName = (TextView) findViewById(R.id.mine_name);
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        CacheUserManager.getInstance().registerLogin(this);
        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
        if(loginBean != null){
            mineHead.setImageResource(R.drawable.my_user_bg_icon);
            mineName.setText(loginBean.getResult().getName());
        } else{
            mineHead.setImageResource(R.drawable.my_user_default);
            mineName.setText("未登录");
        }

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {
        //跳转页面
        Bundle bundle = new Bundle();
        bundle.getInt("img",R.drawable.my_user_bg_icon);
        FrameArouter.getInstance().build(CommonConstant.USER_EXIT_PATH).with(bundle).navigation();
    }

    @Override
    public void onLoginChange(LoginBean loginBean) {
        if(loginBean != null && mineName!=null){
            mineHead.setImageResource(R.drawable.my_user_bg_icon);
            mineName.setText(loginBean.getResult().getName());
        }


    }
}