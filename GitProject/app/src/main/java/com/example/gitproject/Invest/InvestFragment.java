package com.example.gitproject.Invest;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.framework.BaseFragment;
import com.example.gitproject.Invest.manageFinances.ManageFinancesFragment;
import com.example.gitproject.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

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
//拿到布局填充器返回的view后
        ScreenAdapterTools.getInstance().loadView(inflate);
        investSliding = (SlidingTabLayout) inflate.findViewById(R.id.invest_sliding);
        investViewpage = (ViewPager) inflate.findViewById(R.id.invest_viewpage);
    }

    @Override
    protected void initPrensenter() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ManageFinancesFragment());
        fragments.add(new ManageFinancesFragment());
        fragments.add(new ManageFinancesFragment());
        investSliding.setViewPager(investViewpage,new String[]{"全部理财","推荐理财","热门理财"},getActivity(),fragments);

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