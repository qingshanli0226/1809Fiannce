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
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {
    private TabLayout tab1;
    private ViewPager vp1;
    List<Fragment> list;
    List<String> title ;
    private FragmentManager supportFragmentManager;


    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        tab1 = (TabLayout) inflate.findViewById(R.id.tab);
        vp1 = (ViewPager) inflate.findViewById(R.id.vp);


        title = new ArrayList<>();
        title.add("全部理财");
        title.add("推荐理财");
        title.add("热门理财");

        list=new ArrayList<>();
        list.add(new QuanFragment());
        list.add(new TuiFragment());
        list.add(new ReFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), list, title);
        vp1.setAdapter(fragmentAdapter);
        tab1.setupWithViewPager(vp1);


        return inflate;
    }


}
