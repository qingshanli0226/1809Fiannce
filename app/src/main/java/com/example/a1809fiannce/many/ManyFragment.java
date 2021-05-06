package com.example.a1809fiannce.many;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.view.LinView;
import com.example.framwork.call.FiannceARouter;
import com.example.framwork.view.TobView;

public class ManyFragment extends Fragment {
    private LinView us;
    private LinView tell;
    private int i=0;
    private LinView pwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_many, container, false);
        us = (LinView) view.findViewById(R.id.us);
        tell = view.findViewById(R.id.tell);
        pwd = view.findViewById(R.id.pwd);
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiannceARouter.getFiannceARouter().getUserManager().OpenRegisterActivity(getActivity(),null);
            }
        });
        tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+111111));
                startActivity(intent);
            }
        });
        pwd.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {
                if (i==0){
//                    WindowManager manager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//                    layoutParams.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//                    layoutParams.format= PixelFormat.TRANSPARENT;
//                    layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//                    layoutParams.width=700;
//                    layoutParams.height=500;
//                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_window, null);
//                    manager.addView(inflate,layoutParams);
                    pwd.setRightIcon(R.mipmap.toggle_on);
                    i=1;
                }else if (i==1){
                    pwd.setRightIcon(R.mipmap.toggle_off);
                    i=0;
                }
            }
        });
        return view;
    }

}