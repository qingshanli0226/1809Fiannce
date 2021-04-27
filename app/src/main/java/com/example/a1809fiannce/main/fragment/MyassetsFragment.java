package com.example.a1809fiannce.main.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1809fiannce.R;
import com.fiannce.bawei.framework.view.ToolBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyassetsFragment extends Fragment {

    public MyassetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_myassets, container, false);
        return inflate;
    }

}