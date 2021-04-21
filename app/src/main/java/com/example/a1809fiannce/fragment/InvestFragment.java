package com.example.a1809fiannce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.TabCus;
import com.example.a1809fiannce.fragment.InFragment.AllFragment;
import com.example.a1809fiannce.fragment.InFragment.HotFragment;
import com.example.a1809fiannce.fragment.InFragment.RecomFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class InvestFragment extends Fragment {
    private CommonTabLayout comTab;
    private LinearLayout vp;
    private ArrayList<CustomTabEntity> mTabEntitys = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_invest, container, false);
        comTab = (CommonTabLayout) inflate.findViewById(R.id.com_tab);
        vp = (LinearLayout) inflate.findViewById(R.id.vp);
//        mTabEntitys.add(new TabCus("全部理财",R.mipmap.home_true,R.mipmap.home_fase));
//        mTabEntitys.add(new TabCus("推荐理财",R.mipmap.home_true,R.mipmap.home_fase));
//        mTabEntitys.add(new TabCus("热门理财",R.mipmap.home_true,R.mipmap.home_fase));
//
//        comTab.setTabData(mTabEntitys);
//        AllFragment allFragment = new AllFragment();
//        RecomFragment recomFragment = new RecomFragment();
//        HotFragment hotFragment = new HotFragment();
//        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//        beginTransaction.add(R.id.vp,allFragment);
//        beginTransaction.add(R.id.vp,allFragment);
//        beginTransaction.add(R.id.vp,allFragment);
//        beginTransaction.show(allFragment);
//        beginTransaction.hide(recomFragment);
//        beginTransaction.hide(hotFragment);
//        beginTransaction.commit();
//        comTab.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                FragmentTransaction beginTransaction1 = getActivity().getSupportFragmentManager().beginTransaction();
//                if (position==0){
//                    beginTransaction.show(allFragment);
//                    beginTransaction.hide(recomFragment);
//                    beginTransaction.hide(hotFragment);
//                }else if (position==1){
//                    beginTransaction.show(recomFragment);
//                    beginTransaction.hide(allFragment);
//                    beginTransaction.hide(hotFragment);
//                }else if (position==2){
//                    beginTransaction.show(hotFragment);
//                    beginTransaction.hide(recomFragment);
//                    beginTransaction.hide(allFragment);
//                    beginTransaction1.commit();
//                }
//                beginTransaction1.commit();
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });
        return inflate;
    }
}