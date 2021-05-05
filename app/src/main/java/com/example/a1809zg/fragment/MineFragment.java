package com.example.a1809zg.fragment;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.a1809zg.R;

public class MineFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank3, container, false);
//        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//        WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
//        layoutParams.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        layoutParams.format= PixelFormat.TRANSPARENT;
//        layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        layoutParams.width=700;
//        layoutParams.height=500;
//        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.item_window, null);
//        windowManager.addView(rootview,layoutParams);
        return inflate;
    }
}