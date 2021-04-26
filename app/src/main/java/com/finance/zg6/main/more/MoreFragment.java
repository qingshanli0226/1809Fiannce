package com.finance.zg6.main.more;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.finance.framework.BaseFragment;
import com.finance.user.UserActivity;
import com.finance.zg.R;


public class MoreFragment extends BaseFragment {


    private RelativeLayout iconMoreRegister;
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
        iconMoreRegister.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), UserActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void initView() {

        iconMoreRegister = (RelativeLayout) mView.findViewById(R.id.iconMoreRegister);
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