package com.example.a1809fiannce.many;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.view.LinView;
import com.example.framwork.call.FiannceARouter;


public class ManyFragment extends Fragment {
    private LinView us;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_many, container, false);
        us = (LinView) inflate.findViewById(R.id.us);
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiannceARouter.getFiannceARouter().getUserManager().OpenRegisterActivity(getActivity(),null);
            }
        });

        return inflate;
    }
}