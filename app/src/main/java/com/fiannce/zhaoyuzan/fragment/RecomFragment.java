package com.fiannce.zhaoyuzan.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiannce.zhaoyuzan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecomFragment extends Fragment {


    public RecomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recom, container, false);
    }

}
