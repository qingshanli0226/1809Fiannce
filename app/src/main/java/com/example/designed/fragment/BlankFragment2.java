package com.example.designed.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designed.R;
import com.example.designed.adapter.FragmentAdapter;
import com.example.designed.bufragment.QuanFragment;
import com.example.designed.bufragment.ReFragment;
import com.example.designed.bufragment.TuiFragment;
import com.fiannce.bawei.framework.BaseFragment;
import com.fiannce.bawei.framework.IBaseView;
import com.fiannce.bawei.framework.Ifragment;
import com.fiannce.bawei.framework.view.LoadingPage;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends BaseFragment implements IBaseView {
    private TabLayout tab1;
    private ViewPager vp1;
    List<Fragment> list;
    List<String> title ;

    private FragmentManager supportFragmentManager;


    public BlankFragment2() {
        // Required empty public constructor
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        title = new ArrayList<>();
        title.add(getResources().getString(R.string.Allfinancial));
        title.add(getResources().getString(R.string.Recommendafinancial));
        title.add("热门理财");

        list=new ArrayList<>();
        list.add(new QuanFragment());
        list.add(new TuiFragment());
        list.add(new ReFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), list, title);
        vp1.setAdapter(fragmentAdapter);
        tab1.setupWithViewPager(vp1);

    }

    @Override
    protected void initView() {
        tab1 = (TabLayout) findViewById(R.id.tab);
        vp1 = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    protected int getLoutId() {
        return R.layout.fragment_blank_fragment2;
    }


    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }
}
