package com.example.a1809zg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.a1809zg.R;
import com.example.a1809zg.register.RegitsterActivity;


public class MoreFragment extends Fragment {


    private LinearLayout regitster;
    private View inflate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         inflate = inflater.inflate(R.layout.fragment_blank4, container, false);
         initView();
          regitster.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getActivity(), RegitsterActivity.class);
                  startActivity(intent);
              }
          });
        return inflate;
    }

    private void initView() {
        regitster = inflate.findViewById(R.id.regitster);
    }
}