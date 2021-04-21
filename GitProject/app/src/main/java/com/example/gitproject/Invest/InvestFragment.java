package com.example.gitproject.Invest;

import androidx.viewpager.widget.ViewPager;

import com.example.framework.BaseFragment;
import com.example.gitproject.R;
import com.flyco.tablayout.SlidingTabLayout;


public class InvestFragment extends BaseFragment {


    private SlidingTabLayout investSliding;
    private ViewPager investViewpage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initView() {

        investSliding = (SlidingTabLayout) inflate.findViewById(R.id.invest_sliding);
        investViewpage = (ViewPager) inflate.findViewById(R.id.invest_viewpage);
    }

    @Override
    protected void initPrensenter() {
        pathspec 'study' did not match any file(s) known to git

    }

    @Override
    protected void initData() {

    }
}