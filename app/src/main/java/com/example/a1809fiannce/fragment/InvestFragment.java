package com.example.a1809fiannce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1809fiannce.CallBack;
import com.example.a1809fiannce.FragAdapter;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.TabCus;
import com.example.a1809fiannce.TabCus1;
import com.example.a1809fiannce.fragment.InFragment.AllFragment;
import com.example.a1809fiannce.fragment.InFragment.HotFragment;
import com.example.a1809fiannce.fragment.InFragment.RecomFragment;
import com.example.formwork.model.AllBean;
import com.example.formwork.model.HomeBean;
import com.example.formwork.model.UpdateBean;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class InvestFragment extends Fragment  {
    private CommonTabLayout comTab;

    private ArrayList<CustomTabEntity> mTabEntitys = new ArrayList<>();
    private ArrayList<Fragment> fragmentList=new ArrayList<>();

    private CommonTabLayout tab;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_invest, container, false);
        tab = (CommonTabLayout) inflate.findViewById(R.id.tab);

        mTabEntitys.add(new TabCus1("全部理财"));
        mTabEntitys.add(new TabCus1("推荐理财"));
        mTabEntitys.add(new TabCus1("热门理财"));
        fragmentList.add(new AllFragment());
        fragmentList.add(new RecomFragment());
        fragmentList.add(new HotFragment());
        tab.setTabData(mTabEntitys,getActivity(),R.id.vp1,fragmentList);

        return inflate;
    }

}