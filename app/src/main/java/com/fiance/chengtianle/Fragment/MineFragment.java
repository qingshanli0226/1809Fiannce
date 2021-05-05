package com.fiance.chengtianle.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        // Inflate the layout for this fragment
        toolbar =inflate.findViewById(R.id.toolbar);
        imgTx = inflate.findViewById(R.id.img_tx);
        username = inflate.findViewById(R.id.username);
        pay = inflate.findViewById(R.id.pay);
        cash = inflate.findViewById(R.id.cash);
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }




        return inflate;
    }

    @Subscribe(sticky = true)
    public void ss(String msg){
        if (msg.equals("121")){
            username.setText("   已登陆");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }



}