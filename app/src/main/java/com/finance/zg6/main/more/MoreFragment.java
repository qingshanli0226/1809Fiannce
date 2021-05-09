package com.finance.zg6.main.more;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.finance.framework.BaseFragment;
import com.finance.user.register.UserRegisterActivity;
import com.finance.zg.R;
import com.finance.zg6.gesture.GestureActivity;


public class MoreFragment extends BaseFragment {


    private RelativeLayout iconMoreRegister;
    private RelativeLayout iconMoreSecret;
    private ToggleButton toggleOff;
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
            Intent intent = new Intent(getActivity(), UserRegisterActivity.class);
            startActivity(intent);
        });

        toggleOff.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), GestureActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void initView() {

        iconMoreRegister = (RelativeLayout) mView.findViewById(R.id.iconMoreRegister);
        iconMoreSecret = (RelativeLayout) mView.findViewById(R.id.iconMoreSecret);
        toggleOff = (ToggleButton) mView.findViewById(R.id.toggleOff);
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