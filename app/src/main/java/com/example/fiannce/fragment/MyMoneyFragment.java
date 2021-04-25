package com.example.fiannce.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fiannce.R;
import com.example.net.TobView;

public class MyMoneyFragment extends Fragment {

    private TobView tobView;

    public MyMoneyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my_money, container, false);

        tobView = (TobView) inflate.findViewById(R.id.tob);

        tobView.ImgCallBackListener(new TobView.ImgCallBack() {
            @Override
            public void OnLeftImg() {
                Toast.makeText(getContext(), "图片", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnRightImg() {
                Toast.makeText(getContext(), "图片", Toast.LENGTH_SHORT).show();
            }
        });

        return inflate;
    }
}