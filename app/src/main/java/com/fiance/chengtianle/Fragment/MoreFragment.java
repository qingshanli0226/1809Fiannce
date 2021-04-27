package com.fiance.chengtianle.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiance.chengtianle.R;
import com.fiance.framework.MyView.ToolBar;


public class MoreFragment extends Fragment {


    private ToolBar toolbar;
    private TextView tvMoreRegist;
    private ToggleButton toggleMore;
    private TextView tvMoreReset;
    private RelativeLayout rlMoreContact;
    private TextView tvMorePhone;
    private TextView tvMoreFankui;
    private TextView tvMoreShare;
    private TextView tvMoreAbout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_more, container, false);
        // Inflate the layout for this fragment
        toolbar =inflate.findViewById(R.id.toolbar);
        tvMoreRegist = inflate.findViewById(R.id.tv_more_regist);
        toggleMore = inflate.findViewById(R.id.toggle_more);
        tvMoreReset = inflate.findViewById(R.id.tv_more_reset);
        rlMoreContact = inflate.findViewById(R.id.rl_more_contact);
        tvMorePhone = inflate.findViewById(R.id.tv_more_phone);
        tvMoreFankui = inflate.findViewById(R.id.tv_more_fankui);
        tvMoreShare = inflate.findViewById(R.id.tv_more_share);
        tvMoreAbout = inflate.findViewById(R.id.tv_more_about);

        tvMoreRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/user/RegisterActivity").withInt("", 1).navigation();
            }
        });






        return inflate;
    }
}