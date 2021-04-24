package com.example.a1809fiannce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a1809fiannce.R;
import com.example.network.TobView;


public class MyMoneyFragment extends Fragment {

    private TobView tob;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my_money, container, false);
        tob = inflate.findViewById(R.id.tob2);
        tob.ImgCallBackListener(new TobView.ImgCallBack() {
            @Override
            public void OnLeftImg() {

            }

            @Override
            public void OnRightImg() {
                Toast.makeText(getContext(), "这是图片", Toast.LENGTH_SHORT).show();
            }
        });
        return inflate;
    }
}