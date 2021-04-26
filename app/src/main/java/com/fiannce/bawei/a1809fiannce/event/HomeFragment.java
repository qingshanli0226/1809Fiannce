package com.fiannce.bawei.a1809fiannce.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiannce.bawei.a1809fiannce.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {
    private RecyclerView rv;
    private HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

         View rootView = inflater.inflate(R.layout.fragment_home, container,false);
         rv = rootView.findViewById(R.id.rv);
         rv.setLayoutManager(new LinearLayoutManager(getActivity()));
         homeAdapter = new HomeAdapter();

         rv.setAdapter(homeAdapter);

         return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            stringList.add("1809 " + i);
        }

        homeAdapter.update(stringList);

    }
}
