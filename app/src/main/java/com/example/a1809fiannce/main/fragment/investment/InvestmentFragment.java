package com.example.a1809fiannce.main.fragment.investment;


import androidx.fragment.app.Fragment;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.main.fragment.investment.fragment.AllfinancialFragment;
import com.example.a1809fiannce.main.fragment.investment.fragment.HotmoneyFragment;
import com.example.a1809fiannce.main.fragment.investment.fragment.RecommendafinancialFragment;
import com.example.a1809fiannce.main.fragment.more.adapter.MyFragmentAdapter;
import com.fiannce.bawei.framework.BaseFragmnet;
import com.fiannce.bawei.framework.view.mViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestmentFragment extends BaseFragmnet {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private MyFragmentAdapter adapter;
    private TabLayout InvestmentTabLayout;
    private mViewPager InvestmentViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_investment;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        fragmentList.add(new AllfinancialFragment());
        fragmentList.add(new RecommendafinancialFragment());
        fragmentList.add(new HotmoneyFragment());

        stringList.add("全部理财");
        stringList.add("推荐理财");
        stringList.add("热门理财");

        adapter = new MyFragmentAdapter(getChildFragmentManager(),fragmentList,stringList);
        InvestmentViewPager.setAdapter(adapter);
        InvestmentTabLayout.setupWithViewPager(InvestmentViewPager);
    }

    @Override
    protected void initView() {
        InvestmentTabLayout = (TabLayout) mView.findViewById(R.id.Investment_TabLayout);
        InvestmentViewPager = (mViewPager) mView.findViewById(R.id.Investment_view_pager);
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
