package com.example.a1809fiannce.lnvest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.all.AllFragment;
import com.example.a1809fiannce.InFragment.FragAdapter;
import com.example.a1809fiannce.hot.HotFragment;
import com.example.a1809fiannce.InFragment.RecomFragment;
import com.example.a1809fiannce.view.PageView;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class InvestFragment extends Fragment{
    private ArrayList<CustomTabEntity> mTabEntitys = new ArrayList<>();
    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    private ArrayList<String> strings=new ArrayList<>();
    private TabLayout tab;
    private PageView vp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_invest, container, false);
        tab = (TabLayout) inflate.findViewById(R.id.tab);
        vp = (PageView) inflate.findViewById(R.id.vp);
        strings.clear();
        fragmentList.clear();
       mTabEntitys.add(new InvestTabCus("全部理财"));
        mTabEntitys.add(new InvestTabCus("推荐理财"));
        mTabEntitys.add(new InvestTabCus("热门理财"));

        strings.add("全部理财");
        strings.add("推荐理财");
        strings.add("热门理财");

        fragmentList.add(new AllFragment());
        fragmentList.add(new RecomFragment());
        fragmentList.add(new HotFragment());

        //tab.setTabData(mTabEntitys,getActivity(),R.id.vp1,fragmentList);
        FragAdapter fragAdapter = new FragAdapter(getChildFragmentManager(), fragmentList, strings);

        vp.setAdapter(fragAdapter);
        tab.setupWithViewPager(vp);

        return inflate;
    }

}