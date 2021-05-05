package com.example.myapplication.fragment.mymoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.example.demo.Demo.AROUTE_PATH_EXIT_LOGIN;

public class MymoneyFragment extends BaseFragment {


    private ToolBar toolbar;
    private TextView userName;

    @Override
    protected void initData() {

        EventBus.getDefault().register(this);

        SharedPreferences login = getActivity().getSharedPreferences("login", 0);
        String name = login.getString("username", "");
        userName.setText(""+name);

        toolBar.setToolbarListener(new ToolBar.IToolbarListener() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightImgClick() {
                Toast.makeText(getContext(), "picture", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build(AROUTE_PATH_EXIT_LOGIN).navigation();
            }

            @Override
            public void onRightTvClick() {

            }
        });

    }

    @Subscribe(sticky = true)
    public void Events(String event){
        if (event.equals("change_message")){
            userName.setText("");
            SharedPreferences login = getActivity().getSharedPreferences("login", 0);
            SharedPreferences.Editor edit = login.edit();
            edit.putBoolean("is_login",false);
            edit.commit();
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) mView.findViewById(R.id.toolbar);
        userName = (TextView) mView.findViewById(R.id.user_name);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mymoney;
    }


}