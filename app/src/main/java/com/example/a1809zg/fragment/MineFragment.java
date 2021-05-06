package com.example.a1809zg.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.a1809zg.R;

public class MineFragment extends Fragment {


    private TextView llTouzi;
    private TextView llTouziZhiguan;
    private TextView llZichan;
    private View inflate;
    private TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_blank3, container, false);
//        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//        WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
//        layoutParams.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        layoutParams.format= PixelFormat.TRANSPARENT;
//        layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        layoutParams.width=700;
//        layoutParams.height=500;
//        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.item_window, null);
//        windowManager.addView(rootview,layoutParams);
        initView();

        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String name1 = login.getString("name", "");
        boolean islogin = login.getBoolean("islogin", false);
        if (islogin){
            name.setText(name1+"");

        }
        return inflate;
    }

    private void initView() {
        llTouzi = inflate.findViewById(R.id.ll_touzi);
        llTouziZhiguan = inflate.findViewById(R.id.ll_touzi_zhiguan);
        llZichan = inflate.findViewById(R.id.ll_zichan);
        name = inflate.findViewById(R.id.name);
    }
}