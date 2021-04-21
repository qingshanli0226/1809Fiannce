package com.fiannce.zhaoyuzan.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.fiannce.zhaoyuzan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TouZiFragment extends Fragment {


    public TouZiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tou_zi, container, false);
    }

}
