package com.Fiannce.myapplication.fragment.investment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.Fiannce.myapplication.R;
import com.Fiannce.myapplication.adapter.Vpadapter;
import com.Fiannce.myapplication.fragment.investment.money.allmoeny.AllmoneyFragment;
import com.Fiannce.myapplication.fragment.investment.money.HotFragment;
import com.Fiannce.myapplication.fragment.investment.money.RecommendFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestmentFragment extends Fragment {


    private TabLayout tab;
    private ViewPager vp;

    public InvestmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_investment, container, false);
        tab = (TabLayout) inflate.findViewById(R.id.tab);
        vp = (ViewPager) inflate.findViewById(R.id.vp);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllmoneyFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new HotFragment());
        List<String> strings = new ArrayList<>();
        strings.add("全部理财");
        strings.add("推荐理财");
        strings.add("热门理财");


        Vpadapter vpadapter = new Vpadapter(getActivity().getSupportFragmentManager(), fragments, strings);
        vp.setAdapter(vpadapter);
        tab.setupWithViewPager(vp);
        return inflate;
    }

}
