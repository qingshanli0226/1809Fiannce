package com.example.fiannce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fiannce.R;
import com.example.fiannce.adapter.MyTab;
import com.example.fiannce.fragment.fragment_invest.AllFragment;
import com.example.fiannce.fragment.fragment_invest.HotFragment;
import com.example.fiannce.fragment.fragment_invest.RecomFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

public class InvestFragment extends Fragment {

    private CommonTabLayout common;

    private ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
    private ArrayList<Fragment> list = new ArrayList<>();

    public InvestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_invest, container, false);

        common = (CommonTabLayout) inflate.findViewById(R.id.common);

        customTabEntities.add(new MyTab("全部理财",0,0));
        customTabEntities.add(new MyTab("推荐理财",0,0));
        customTabEntities.add(new MyTab("热门理财",0,0));

        list.add(new AllFragment());
        list.add(new RecomFragment());
        list.add(new HotFragment());

        common.setTabData(customTabEntities,getActivity(),R.id.ll,list);

        return inflate;
    }
}