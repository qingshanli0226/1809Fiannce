package com.finance.zg6.mianInsideFragment;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.finance.framework.BaseFragment;
import com.finance.zg.R;


public class MoreFragment extends BaseFragment {


    private RelativeLayout iconMoreRegist;
    private RelativeLayout iconMoreSecret;
    private ImageView toggleOff;
    private RelativeLayout iconMoreReset;
    private RelativeLayout iconMoreContact;
    private RelativeLayout iconMoreSms;
    private RelativeLayout iconMoreShare;
    private RelativeLayout iconMoreAbout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        iconMoreRegist = (RelativeLayout) mView.findViewById(R.id.iconMoreRegist);
        iconMoreSecret = (RelativeLayout) mView.findViewById(R.id.iconMoreSecret);
        toggleOff = (ImageView) mView.findViewById(R.id.toggleOff);
        iconMoreReset = (RelativeLayout) mView.findViewById(R.id.iconMoreReset);
        iconMoreContact = (RelativeLayout) mView.findViewById(R.id.iconMoreContact);
        iconMoreSms = (RelativeLayout) mView.findViewById(R.id.iconMoreSms);
        iconMoreShare = (RelativeLayout) mView.findViewById(R.id.iconMoreShare);
        iconMoreAbout = (RelativeLayout) mView.findViewById(R.id.iconMoreAbout);
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