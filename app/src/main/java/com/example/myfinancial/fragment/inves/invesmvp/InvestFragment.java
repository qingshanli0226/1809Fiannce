package com.example.myfinancial.fragment.inves.invesmvp;

import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import com.example.framework.BaseFragment;
import com.example.framework.adapter.VpAdapter;
import com.example.myfinancial.R;
import com.example.myfinancial.fragment.inves.fragment.AllFragment;
import com.example.myfinancial.fragment.inves.fragment.HotFragment;
import com.example.myfinancial.fragment.inves.fragment.ReCommendFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class InvestFragment extends BaseFragment {

    private ViewPager vp;
    private TabLayout tab;

    private List<Fragment> list=new ArrayList<>();
    private List<String> strings=new ArrayList<>();
    private VpAdapter vpAdapter;

    @Override
    public int getbandLayout() {
        return R.layout.fragment_invest;
    }

    @Override
    public void initData() {
        list.add(new AllFragment());
        list.add(new ReCommendFragment());
        list.add(new HotFragment());

        strings.add(getString(R.string.allmoneyManagement));
        strings.add(getString(R.string.recommendmoneyManagement));
        strings.add(getString(R.string.h0tmoneyManagement));

        vpAdapter=new VpAdapter(getChildFragmentManager(),list,strings);

        vp.setAdapter(vpAdapter);
        tab.setupWithViewPager(vp);

    }

    @Override
    public void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void initPresenter() {

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