package com.finance.zg6.main.myassets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.finance.framework.BaseFragment;
import com.finance.framework.view.ToolBar;
import com.finance.user.login.UserLoginActivity;
import com.finance.zg.R;


public class MyAssetsFragment extends BaseFragment {


    private ToolBar toolbar;
    private ImageView myAssetsSetting;
    private ImageView userImg;
    private Button rechargeBt;
    private Button withdrawalBt;
    private RelativeLayout myInvestManage;
    private RelativeLayout myRewardManage;
    private RelativeLayout myAssetManage;
    private TextView userName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_assets;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        SharedPreferences user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if (user.getBoolean("isLogin", false)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("提示");
            builder.setMessage("你还没有登录哦！么么~");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    ARouter.getInstance().build("/UserLoginActivity/MainActivity").withInt("",1).navigation();
                    Intent intent = new Intent(getActivity(), UserLoginActivity.class);
                    startActivity(intent);
                }
            });
//            builder.create().show();

        } else {
            userName.setText(""+user.getString("user","123"));
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
        userName = (TextView) mView.findViewById(R.id.userName);
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