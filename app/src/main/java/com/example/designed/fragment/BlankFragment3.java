package com.example.designed.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.designed.R;
import com.fiannce.bawei.framework.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends BaseFragment  {

    private ImageView setting;
    private ImageView pic;
    private TextView moery;
    private TextView withdraw;
    private LinearLayout invest;
    private LinearLayout manage;
    private LinearLayout property;

    public BlankFragment3() {
        // Required empty public constructor
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setting = (ImageView) findViewById(R.id.setting);
        pic = (ImageView) findViewById(R.id.pic);
        moery = (TextView) findViewById(R.id.moery);
        withdraw = (TextView) findViewById(R.id.withdraw);
        invest = (LinearLayout) findViewById(R.id.invest);
        manage = (LinearLayout) findViewById(R.id.manage);
        property = (LinearLayout) findViewById(R.id.property);

    }

    @Override
    protected int getLoutId() {
        return R.layout.fragment_blank_fragment3;
    }

}
