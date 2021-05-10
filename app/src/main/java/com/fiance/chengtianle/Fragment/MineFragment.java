package com.fiance.chengtianle.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiance.chengtianle.QuitActivity;
import com.fiance.chengtianle.R;
import com.fiance.framework.MyView.ToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class MineFragment extends Fragment {
    private ToolBar toolbar;
    private ImageView imgTx;
    private TextView username;
    private ImageView pay;
    private ImageView cash;
    public  static String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        toolbar =inflate.findViewById(R.id.toolbar);
        imgTx = inflate.findViewById(R.id.img_tx);
        username = inflate.findViewById(R.id.username);
        pay = inflate.findViewById(R.id.pay);
        cash = inflate.findViewById(R.id.cash);

        SharedPreferences sflogin = getActivity().getSharedPreferences("sflogin", Context.MODE_PRIVATE);
         name = sflogin.getString("username", "未登录");
        boolean islogin = sflogin.getBoolean("islogin", false);
        if (islogin){
            username.setText(name);
        }
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (islogin){
                    Intent intent = new Intent(getActivity(), QuitActivity.class);
                    startActivity(intent);
                }else{
                    ARouter.getInstance().build("/login/LoginActivity").withInt("", 1).navigation();
                }
            }
        });
        return inflate;
    }


}
