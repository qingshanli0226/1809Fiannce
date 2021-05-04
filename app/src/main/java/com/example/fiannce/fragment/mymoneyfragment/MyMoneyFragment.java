package com.example.fiannce.fragment.mymoneyfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fiannce.R;
import com.example.net.ToolBarView;

public class MyMoneyFragment extends Fragment {

    private ToolBarView tobView;

    public MyMoneyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my_money, container, false);

        tobView = (ToolBarView) inflate.findViewById(R.id.tob);

        tobView.ImgCallBackListener(new ToolBarView.ImgCallBack() {
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