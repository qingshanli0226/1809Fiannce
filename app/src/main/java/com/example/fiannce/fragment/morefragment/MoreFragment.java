package com.example.fiannce.fragment.morefragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fiannce.R;
import com.example.fiannce.fragment.morefragment.gesture.GestureActivity;
import com.example.framework.FiannceARouter;

public class MoreFragment extends Fragment {

    private MoreView reg;
    private MoreView tell;
    private int i = 0;
    private MoreView pwd;
    private boolean isBoolean;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_more, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA},101);
        }

        reg = (MoreView) inflate.findViewById(R.id.reg);
        tell = (MoreView) inflate.findViewById(R.id.tell);
        pwd = (MoreView) inflate.findViewById(R.id.pwd);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiannceARouter.getFiannceARouter().getUserManager().OpenRegisterActivity(getActivity(),null);
            }
        });

        tell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+123456));
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pwd", Context.MODE_PRIVATE);
        boolean isPwd = sharedPreferences.getBoolean("isPwd", false);

        isBoolean = isPwd;

        if (isPwd){
            pwd.setRightIcon(R.mipmap.toggle_on);
        }else {
            pwd.setRightIcon(R.mipmap.toggle_off);
        }

        pwd.setiRightImgCallBack(new MoreView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {
                if (isBoolean){
                    isBoolean = false;

                    pwd.setRightIcon(R.mipmap.toggle_off);
                    Toast.makeText(getActivity(), "关闭手势密码", Toast.LENGTH_SHORT).show();
                }else {
                    isBoolean = true;

                    pwd.setRightIcon(R.mipmap.toggle_on);
                    Toast.makeText(getActivity(), "开始手势密码", Toast.LENGTH_SHORT).show();
                    if (PwdManage.getManage().isPwd()){
                        PwdManage.getManage().setPwd(false);

                        Intent intent = new Intent(getContext(), GestureActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });

        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pwd", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("isPwd",isBoolean);
        edit.commit();
    }
}