package com.fiannce.myapplication.fragment.investment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fiannce.framework.BaseFragment;
import com.fiannce.myapplication.R;
import com.fiannce.myapplication.adapter.Vpadapter;
import com.fiannce.myapplication.fragment.investment.money.HotFragment;
import com.fiannce.myapplication.fragment.investment.money.RecommendFragment;
import com.fiannce.myapplication.fragment.investment.money.allmoeny.AllmoneyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestmentFragment extends BaseFragment {


    private TabLayout tab;
    private ViewPager vp;

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        tab = (TabLayout) mView.findViewById(R.id.tab);
        vp = (ViewPager) mView.findViewById(R.id.vp);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllmoneyFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new HotFragment());
        List<String> strings = new ArrayList<>();
        strings.add("全部理财");
        strings.add("推荐理财");
        strings.add("热门理财");
        Vpadapter vpadapter = new Vpadapter(getActivity().getSupportFragmentManager(), fragments, strings);
        vp.setAdapter(vpadapter);
        tab.setupWithViewPager(vp);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_investment;
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
