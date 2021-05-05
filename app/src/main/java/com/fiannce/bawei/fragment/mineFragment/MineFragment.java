package com.fiannce.bawei.fragment.mineFragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.fiannce.framework.BaseFragment;
import com.fiannce.framework.view.ToolBar;
import com.fiannce.net.mode.StringBean;
import com.fiannce.user.UserManager;
import com.fiannce.user.exitLogin.ExitLoginActivity;
import com.fiannce.user.login.LoginActivity;
import com.fiannce.zhaoyuzan.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {


    private ToolBar toolbar;
    private ImageView ivMeIcon;
    private TextView tvMeName;
    private ImageView recharge;
    private ImageView withdraw;
    private TextView llTouzi;
    private TextView llTouziZhiguan;
    private TextView llZichan;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

        if (UserManager.getInstance().hasUserInfo(getActivity())) {

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("提示");
            builder.setMessage("您还没有登录哦！");
            builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(getString(R.string.cancle), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }


    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        toolbar = mBaseView.findViewById(R.id.toolbar);
        ivMeIcon = mBaseView.findViewById(R.id.iv_me_icon);
        recharge = mBaseView.findViewById(R.id.recharge);
        withdraw = mBaseView.findViewById(R.id.withdraw);
        llTouzi = mBaseView.findViewById(R.id.ll_touzi);
        llTouziZhiguan = mBaseView.findViewById(R.id.ll_touzi_zhiguan);
        llZichan = mBaseView.findViewById(R.id.ll_zichan);
    }

    @Subscribe
    public void event(StringBean bundle){
        tvMeName = mBaseView.findViewById(R.id.tv_me_name);
        final String name = bundle.getName();
        Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
        tvMeName.setText(name+"");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    public void onRightImgClick() {
        super.onRightImgClick();
        Intent intent = new Intent(getActivity(), ExitLoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
