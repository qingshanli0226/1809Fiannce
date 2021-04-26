package com.example.myapplication.fragment.invest;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.framework.BaseFragment;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyFragmentAdapter;
import com.example.myapplication.fragment.invest.allinvest.AllFinancialFragment;
import com.example.myapplication.fragment.invest.hotinvest.HotFinancialFragment;
import com.example.myapplication.fragment.invest.recommendinvest.RecommendFinancialFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class InvestFragment extends BaseFragment {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private TabLayout moneyTab;
    private ViewPager moneyVp;
    private MyFragmentAdapter adapter;

    @Override
    protected void initData() {

        fragmentList.add(new AllFinancialFragment());
        fragmentList.add(new RecommendFinancialFragment());
        fragmentList.add(new HotFinancialFragment());

        stringList.add("全部理财");
        stringList.add("推荐理财");
        stringList.add("热门理财");

        adapter = new MyFragmentAdapter(getChildFragmentManager(),fragmentList,stringList);
        moneyVp.setAdapter(adapter);
        moneyTab.setupWithViewPager(moneyVp);


    }


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        moneyTab = (TabLayout) mView.findViewById(R.id.money_tab);
        moneyVp = (ViewPager) mView.findViewById(R.id.money_vp);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
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