package com.fiannce.bawei.fragment.moreFragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.fiannce.framework.view.ToolBar;
import com.fiannce.user.register.RegisterActivity;
import com.fiannce.zhaoyuzan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {


    private ToolBar toolbar;
    private TextView tvMoreRegist;
    private ToggleButton toggleMore;
    private TextView tvMoreReset;
    private TextView tvMorePhone;
    private TextView tvMoreFankui;
    private TextView tvMoreShare;
    private TextView tvMoreAbout;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_more, container, false);

        toolbar = inflate.findViewById(R.id.toolbar);
        tvMoreRegist = inflate.findViewById(R.id.tv_more_regist);
        toggleMore = inflate.findViewById(R.id.toggle_more);
        tvMoreReset = inflate.findViewById(R.id.tv_more_reset);
        tvMorePhone = inflate.findViewById(R.id.tv_more_phone);
        tvMoreFankui = inflate.findViewById(R.id.tv_more_fankui);
        tvMoreShare = inflate.findViewById(R.id.tv_more_share);
        tvMoreAbout = inflate.findViewById(R.id.tv_more_about);

        tvMoreRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ARouter.getInstance().build("/user/RegisterActivity").withInt("",1).navigation();
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        return inflate;
    }
}
