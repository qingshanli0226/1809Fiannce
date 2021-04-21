package com.example.a1809fiannce.investment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.investment.allfinancial.AllfinancialFragment;
import com.example.a1809fiannce.investment.claCommend.ClaCommendFragment;
import com.example.a1809fiannce.investment.hotmoney.HotmoneyFragment;
import com.example.framework.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class InvestmentFragment extends BaseFragment {
    private TabLayout investmentTab;
    private ViewPager investmentVp;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_investment;
    }

    @Override
    protected void initView() {
        investmentTab = (TabLayout) findViewById(R.id.investment_tab);
        investmentVp = (ViewPager) findViewById(R.id.investment_vp);

    }

    @Override
    protected void initData() {
        List<Fragment> frag=new ArrayList<>();
        List<String> tabstr=new ArrayList<>();

        frag.add(new AllfinancialFragment());
        frag.add(new ClaCommendFragment());
        frag.add(new HotmoneyFragment());

        tabstr.add("全部理财");
        tabstr.add("推荐理财");
        tabstr.add("热门理财");

        MyFragAdapter myFragAdapter = new MyFragAdapter(getFragmentManager(), frag, tabstr);
        investmentVp.setAdapter(myFragAdapter);
        investmentTab.setupWithViewPager(investmentVp);
    }

    @Override
    protected void initPresenter() {

    }

    public class MyFragAdapter extends FragmentPagerAdapter {
        private List<Fragment> frag;
        private List<String> tabstr;

        public MyFragAdapter(@NonNull FragmentManager fm, List<Fragment> frag, List<String> tabstr) {
            super(fm);
            this.frag = frag;
            this.tabstr = tabstr;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return frag.get(position);
        }

        @Override
        public int getCount() {
            return frag.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabstr.get(position);
        }
    }
}