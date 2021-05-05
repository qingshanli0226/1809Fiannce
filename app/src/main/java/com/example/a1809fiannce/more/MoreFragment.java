package com.example.a1809fiannce.more;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.a1809fiannce.R;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.view.ToolBar;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public class MoreFragment extends BaseFragment {
    private ToolBar toolbar;
    private RelativeLayout register;
    private RelativeLayout moneyManagement;
    private RelativeLayout morePhone;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        register = (RelativeLayout) findViewById(R.id.more_register);
        moneyManagement = (RelativeLayout) findViewById(R.id.money_management);
        morePhone = (RelativeLayout) findViewById(R.id.more_phone);
    }

    @Override
    protected void initData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiannceArouter.getInstance().getIUserInterface().openRegisterActivity(getContext(),null);
            }
        });

        moneyManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiannceArouter.getInstance().getIUserInterface().openLoginActivity(getContext(),null);
            }
        });

        morePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:010-56253825"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPresenter() {

    }
}