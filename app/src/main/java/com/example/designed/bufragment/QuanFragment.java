package com.example.designed.bufragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.example.designed.R;
import com.example.designed.adapter.LiAdapter;
import com.fiannce.bawei.framework.manager.CacheManager;
import com.fiannce.bawei.net.model.Libean;

import java.util.ArrayList;
import java.util.List;

import top.littlefogcat.danmakulib.danmaku.Danmaku;
import top.littlefogcat.danmakulib.danmaku.DanmakuManager;

import static top.littlefogcat.danmakulib.danmaku.Danmaku.COLOR_RED;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanFragment extends Fragment {

    private FrameLayout fragment;
    private RecyclerView rv;
    List<Libean.ResultBean> list = new ArrayList<>();

    public QuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_quan, container, false);
        fragment = (FrameLayout) inflate.findViewById(R.id.fragment);
        rv = (RecyclerView) inflate.findViewById(R.id.rv);

        Libean libean = CacheManager.getInstance().getLibean();
        list.addAll(libean.getResult());

        DanmakuManager dm = DanmakuManager.getInstance();
        dm.setDanmakuContainer(fragment);
        dm.init(getContext(),fragment);

        Danmaku danmaku = new Danmaku();
        danmaku.text="有道金融壕运当头,首投返现最高达188元";
        danmaku.size=100;
        danmaku.color = COLOR_RED;
        dm.send(danmaku);

        LiAdapter liAdapter = new LiAdapter(R.layout.layout, list);
        rv.setAdapter(liAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        return inflate;
    }

}
