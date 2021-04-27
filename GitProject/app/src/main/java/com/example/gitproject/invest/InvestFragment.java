package com.example.gitproject.invest;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.framework.BaseFragment;
import com.example.gitproject.invest.hot.HotFragment;
import com.example.gitproject.invest.manageFinances.ManageFinancesFragment;
import com.example.gitproject.invest.recommend.RecommendFragment;
import com.example.gitproject.R;
import com.flyco.tablayout.SlidingTabLayout;


import java.util.ArrayList;


public class InvestFragment extends BaseFragment {


    private SlidingTabLayout investSliding;
    private ViewPager investViewpage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initView() {

        investSliding = (SlidingTabLayout) findViewById(R.id.invest_sliding);
        investViewpage = (ViewPager) findViewById(R.id.invest_viewpage);
    }

    @Override
    protected void initPrensenter() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ManageFinancesFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new HotFragment());
        investSliding.setViewPager(investViewpage,new String[]{getString(R.string.invest_one),getString(R.string.invest_two),getString(R.string.invest_three)},getActivity(),fragments);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }
}