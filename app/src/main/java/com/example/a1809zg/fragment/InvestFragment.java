package com.example.a1809zg.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.a1809zg.R;
import com.example.a1809zg.inverstment.RecomFragment;
import com.example.frame.MyToolbar;
import com.example.a1809zg.inverstment.HotFragment;
import com.example.a1809zg.inverstment.AllFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class InvestFragment extends Fragment {


    private TabLayout tab;
    private ViewPager vp;
    private View inflate;
    private FragPageAdapter fragPageAdapter;
    private List<Fragment> list = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private MyToolbar bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_blank2, container, false);
        initView();

        list.add(new AllFragment());
        list.add(new RecomFragment());
        list.add(new HotFragment());
        data.add("全部理财");
        data.add("推荐理财");
        data.add("热门理财");
        fragPageAdapter = new FragPageAdapter(getActivity().getSupportFragmentManager(), list, data);
        vp.setAdapter(fragPageAdapter);
        tab.setupWithViewPager(vp);
        bar.setToolbarlistenr(new MyToolbar.ItoolbarListenr() {
            @Override
            public void onLeftClick() {
                Toast.makeText(getActivity(), "666", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {

            }

            @Override
            public void onTextClick() {

            }
        });
        return inflate;
    }

    private void initView() {
        tab = inflate.findViewById(R.id.tab);
        vp = inflate.findViewById(R.id.vp);
        bar = inflate.findViewById(R.id.bar);
    }
}