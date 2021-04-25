package com.example.a1809fiannce.main.invest;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.adapter.InvestAdapter;
import com.example.a1809fiannce.main.invest.allfinancial.AllFragment;
import com.example.a1809fiannce.main.invest.hotfinancial.HotFragment;
import com.example.a1809fiannce.main.invest.recommend.RecommendFragment;
import com.example.framework.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class InvestFragment extends BaseFragment {

    private TabLayout fragInvestTab;
    private ViewPager fragInvestVp;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new HotFragment());
        ArrayList<String> strings = new ArrayList<>();
        strings.add(getResources().getString(R.string.all_financial));
        strings.add(getResources().getString(R.string.recommend_financial));
        strings.add(getResources().getString(R.string.hot_financial));

        InvestAdapter fragmentAdapter = new InvestAdapter(getActivity().getSupportFragmentManager(), fragments, strings);
        fragInvestVp.setAdapter(fragmentAdapter);
        fragInvestTab.setupWithViewPager(fragInvestVp);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        fragInvestTab = (TabLayout) findViewById(R.id.frag_invest_tab);
        fragInvestVp = (ViewPager) findViewById(R.id.frag_invest_vp);
    }
}