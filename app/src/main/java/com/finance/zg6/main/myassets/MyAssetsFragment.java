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
import com.finance.framework.view.ToolBar;
import com.finance.net.bean.UserBean;
import com.finance.user.login.UserLoginActivity;
import com.finance.zg.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class MyAssetsFragment extends BaseFragment {


    private ToolBar toolbar;
    private ImageView myAssetsSetting;
    private ImageView userImg;
    private Button rechargeBt;
    private Button withdrawalBt;
    private RelativeLayout myInvestManage;
    private RelativeLayout myRewardManage;
    private RelativeLayout myAssetManage;
    private TextView userNameTv;

    private String userName;
    private String passWord;
    private  boolean login;
    private  AlertDialog.Builder builder;
    private SharedPreferences user;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_assets;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        userNameTv.setText(""+user.getString("user",""));



        if (user.getBoolean("islogin",false)) {
            builder = new AlertDialog.Builder(getContext());
            builder.setTitle("提示");
            builder.setMessage("你还没有登录哦！么么~");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getActivity(), UserLoginActivity.class);
                    startActivity(intent);
                }
            });
            builder.create().show();
        }else {

        }

    }



    @Override
    protected void initView() {

        toolbar = (ToolBar) mView.findViewById(R.id.toolbar);
        myAssetsSetting = (ImageView) mView.findViewById(R.id.myAssetsSetting);
        userImg = (ImageView) mView.findViewById(R.id.userImg);
        rechargeBt = (Button) mView.findViewById(R.id.rechargeBt);
        withdrawalBt = (Button) mView.findViewById(R.id.withdrawalBt);
        myInvestManage = (RelativeLayout) mView.findViewById(R.id.myInvestManage);
        myRewardManage = (RelativeLayout) mView.findViewById(R.id.myRewardManage);
        myAssetManage = (RelativeLayout) mView.findViewById(R.id.myAssetManage);
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