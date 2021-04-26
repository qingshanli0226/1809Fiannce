package com.example.a1809zg.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.a1809zg.Adapter;
import com.example.a1809zg.R;
import com.example.a1809zg.myview.Toolbar;
import com.example.a1809zg.infragment.AskFragment2;
import com.example.a1809zg.infragment.HotFragment3;
import com.example.a1809zg.infragment.MoneyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment2 extends Fragment {


    private TabLayout tab;
    private ViewPager vp;
    private View inflate;
    private Adapter adapter;
    private List<Fragment> list = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private Toolbar bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_blank2, container, false);
        initView();

        list.add(new MoneyFragment());
        list.add(new AskFragment2());
        list.add(new HotFragment3());
        data.add("全部理财");
        data.add("推荐理财");
        data.add("热门理财");
        adapter = new Adapter(getActivity().getSupportFragmentManager(), list, data);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        bar.setToolbarlistenr(new Toolbar.ItoolbarListenr() {
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