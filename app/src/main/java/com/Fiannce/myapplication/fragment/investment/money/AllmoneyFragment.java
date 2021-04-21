package com.Fiannce.myapplication.fragment.investment.money;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Fiannce.myapplication.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllmoneyFragment extends Fragment {


    public AllmoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_allmoney, container, false);

        List<Fragment> fragments;
        List<String> strings;
        return inflate;
    }

}
