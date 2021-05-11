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

import com.example.a1809fiannce.Gesture.GestureActivity;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.view.LinView;
import com.example.framwork.call.FiannceARouter;
import com.example.framwork.view.TobView;

public class ManyFragment extends Fragment {
    private LinView us;
    private LinView tell;
    private int i=0;
    private LinView pwd;
    private boolean isBoolean;
    private LinView reset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_many, container, false);

        us = (LinView) view.findViewById(R.id.us);
        tell = view.findViewById(R.id.tell);
        pwd = view.findViewById(R.id.pwd);
        reset = view.findViewById(R.id.reset);

        //注册
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiannceARouter.getFiannceARouter().getUserManager().OpenUserActivity(getActivity(),null);
            }
        });
        us.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {

            }
        });

        //打电话
        tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+111111));
                startActivity(intent);
            }
        });

        tell.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {

            }
        });

        //读取sp判断是否打开手势密码
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pwd", Context.MODE_PRIVATE);
        boolean isPwd = sharedPreferences.getBoolean("isPwd", false);
        isBoolean=isPwd;
        if (isPwd){

            pwd.setRightIcon(R.mipmap.toggle_on);

        }else {

            pwd.setRightIcon(R.mipmap.toggle_off);

        }

        //手势密码点击事件
        pwd.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {
                if (isBoolean){
                    isBoolean=false;
                    pwd.setRightIcon(R.mipmap.toggle_off);
                    Toast.makeText(getContext(), "关闭手势密码", Toast.LENGTH_SHORT).show();

                }else {
                    isBoolean=true;
                    pwd.setRightIcon(R.mipmap.toggle_on);
                    Toast.makeText(getContext(), "开启手势密码", Toast.LENGTH_SHORT).show();
                    if (PwdManage.getManage().isPwd()){
                        PwdManage.getManage().setPwd(false);
                        Intent intent = new Intent(getContext(), GestureActivity.class);
                        intent.putExtra("name","set");
                        startActivity(intent);
                    }
                }
            }
        });
        //重置手势密码点击
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GestureActivity.class);
                intent.putExtra("name","set");
                startActivity(intent);
            }
        });
        //重置手势密码右边图片点击
        reset.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {

            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在Many页面关闭时,把手势密码关闭和打开存储起来
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pwd", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("isPwd",isBoolean);
        edit.commit();
    }
}