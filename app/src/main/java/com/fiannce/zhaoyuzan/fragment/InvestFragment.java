package com.fiannce.zhaoyuzan.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fiannce.zhaoyuzan.R;
import com.fiannce.zhaoyuzan.adapter.FragAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> list = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private FragAdapter fragAdapter;

    public InvestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_invest, container, false);

        tabLayout = inflate.findViewById(R.id.investTab);
        viewPager = inflate.findViewById(R.id.investVp);

        list.add("全部理财");
        list.add("推荐理财");
        list.add("热门理财");

        fragments.add(new AllFragment());
        fragments.add(new RecomendFragment());
        fragments.add(new HotFragment());

        fragAdapter = new FragAdapter(getActivity().getSupportFragmentManager(),fragments,list);

        viewPager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return inflate;
    }

}
