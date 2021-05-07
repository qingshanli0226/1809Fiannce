package com.example.a1809fiannce.many;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

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
        //注册
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiannceARouter.getFiannceARouter().getUserManager().OpenRegisterActivity(getActivity(),null);
            }
        });
        //打电话
        tell.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+111111));
                startActivity(intent);
            }
        });
        //读取sp判断是否打开手势密码
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pwd", Context.MODE_PRIVATE);
        boolean isPwd = sharedPreferences.getBoolean("isPwd", false);
        if (isPwd){
            pwd.setRightIcon(R.mipmap.toggle_on);

        }else {
            pwd.setRightIcon(R.mipmap.toggle_off);
        }
        //手势密码点击事件
        pwd.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {
                if (isPwd){
                    pwd.setRightIcon(R.mipmap.toggle_off);
                    Toast.makeText(getContext(), "关闭手势密码", Toast.LENGTH_SHORT).show();
                    SharedPreferences CloseSharedPreferences = getActivity().getSharedPreferences("pwd", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = CloseSharedPreferences.edit();
                    edit.putBoolean("isPwd",false);
                    edit.commit();
                }else {
                    pwd.setRightIcon(R.mipmap.toggle_on);
                    Toast.makeText(getContext(), "开启手势密码", Toast.LENGTH_SHORT).show();
                    SharedPreferences ShowSharedPreferences = getActivity().getSharedPreferences("pwd", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = ShowSharedPreferences.edit();
                    edit.putBoolean("isPwd",true);
                    edit.commit();
                }

//                    WindowManager manager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//                    layoutParams.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//                    layoutParams.format= PixelFormat.TRANSPARENT;
//                    layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//                    layoutParams.width=700;
//                    layoutParams.height=500;
//                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_window, null);
//                    manager.addView(inflate,layoutParams);
            }
        });
        return view;
    }

}