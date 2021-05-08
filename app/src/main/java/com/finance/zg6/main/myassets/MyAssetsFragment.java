package com.finance.zg6.main.myassets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.framework.BaseFragment;
import com.finance.framework.manager.CacheManager;
import com.finance.framework.manager.CacheUserManager;
import com.finance.framework.view.ToolBar;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.UserBean;
import com.finance.user.login.UserLoginActivity;
import com.finance.zg.R;
import com.finance.zg6.main.MainActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class MyAssetsFragment extends BaseFragment {

    private ImageView myAssetsSetting;
    private TextView userNameTv;

    private LoginBean loginBean;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_assets;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        loginBean = CacheUserManager.getInstance().getLoginBean();



        if (loginBean!=null){
            userNameTv.setText(""+loginBean.getResult().getName());
        }else {
            userNameTv.setText("");
        }

        myAssetsSetting.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(),ProlifePicActivity.class);
            intent.putExtra("img",R.drawable.user_img);
            startActivity(intent);
        });
    }

    @Override
    protected void initView() {

        myAssetsSetting = (ImageView) mView.findViewById(R.id.myAssetsSetting);
        userNameTv = (TextView) mView.findViewById(R.id.userName);
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