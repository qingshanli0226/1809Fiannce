package com.fiannce.bawei.fragment.moreFragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.fiannce.bawei.gesturelock.GestureLockActivity;
import com.fiannce.bawei.gesturelock.GesturePresenter;
import com.fiannce.framework.manager.CacheManager;
import com.fiannce.framework.manager.CacheUserManager;
import com.fiannce.framework.view.ToolBar;
import com.fiannce.user.login.LoginActivity;
import com.fiannce.user.register.RegisterActivity;
import com.fiannce.zhaoyuzan.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {


    private ToolBar toolbar;
    private TextView tvMoreRegist;
    private ImageView toggleMore;
    private TextView tvMoreReset;
    private TextView tvMorePhone;
    private TextView tvMoreFankui;
    private TextView tvMoreShare;
    private TextView tvMoreAbout;
    private TextView tvPhone;
    private RelativeLayout fragMoreVerifyPwd;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_more, container, false);

        toolbar = inflate.findViewById(R.id.toolbar);
        tvMoreRegist = inflate.findViewById(R.id.tv_more_regist);
        toggleMore = inflate.findViewById(R.id.toggle_more);
        tvMoreReset = inflate.findViewById(R.id.tv_more_reset);
        tvMorePhone = inflate.findViewById(R.id.tv_more_phone);
        tvMoreFankui = inflate.findViewById(R.id.tv_more_fankui);
        tvMoreShare = inflate.findViewById(R.id.tv_more_share);
        tvMoreAbout = inflate.findViewById(R.id.tv_more_about);
        tvPhone = inflate.findViewById(R.id.tv_phone);
        fragMoreVerifyPwd = inflate.findViewById(R.id.frag_more_verify_pwd);

        tvMoreRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ARouter.getInstance().build("/user/RegisterActivity").withInt("",1).navigation();
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvMorePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.conect));
                builder.setMessage(getString(R.string.yseOrNo));
                builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(getString(R.string.tel) + tvPhone));
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton(getString(R.string.cancle), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

        toggleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleMore.setImageResource(R.drawable.toggle_on);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("开启手势密码");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        toggleMore.setImageResource(R.drawable.toggle_off);
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (CacheUserManager.getInstance().getLoginBean() != null) {
                            Intent intent = new Intent(getActivity(), GestureLockActivity.class);
                            intent.putExtra("judge",1);
                            startActivity(intent);
                        } else {
                            toggleMore.setImageResource(R.drawable.toggle_off);
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
            }
        });

        fragMoreVerifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CacheManager.getInstance().gesture) {
                    Intent intent = new Intent(getActivity(),GestureLockActivity.class);
                    intent.putExtra("judge",3);
                    toggleMore.setImageResource(R.drawable.toggle_off);
                } else {
                    Toast.makeText(getActivity(), "请检查是否开启了手势密码", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return inflate;
    }


}
