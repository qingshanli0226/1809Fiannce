package com.finance.zg6.mianInsideFragment;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.finance.framework.BaseFragment;
import com.finance.framework.view.ToolBar;
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_assets;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

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