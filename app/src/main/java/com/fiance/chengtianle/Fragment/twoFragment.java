package com.fiance.chengtianle.Fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fiance.chengtianle.Adapter.MyVp;
import com.fiance.chengtianle.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class twoFragment extends Fragment {


    private TabLayout tab;
    private ViewPager vp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_two, container, false);
        // Inflate the layout for this fragment
        tab =inflate.findViewById(R.id.tab);
        vp =inflate.findViewById(R.id.vp);

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new aFragment());
        list.add(new bFragment());
        list.add(new cFragment());

        ArrayList<String> title = new ArrayList<>();
        title.add("全部理财");
        title.add("推荐理财");
        title.add("热门理财");

        MyVp myVp = new MyVp(getActivity().getSupportFragmentManager(), list, title);
        vp.setAdapter(myVp);
        tab.setupWithViewPager(vp);
        tab.setTabRippleColor(ColorStateList.valueOf(getResources().getColor(R.color.black)));

        return inflate;
    }
}