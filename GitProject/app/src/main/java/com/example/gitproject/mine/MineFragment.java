package com.example.gitproject.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.common.CommonConstant;
import com.example.common.SpUtil;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.framework.view.ToolBar;
import com.example.gitproject.R;
import com.example.net.bean.LoginBean;

import java.io.File;


public class MineFragment extends BaseFragment implements CacheUserManager.ILoginChange {



    private ImageView mineHead;
    private TextView mineName;
    private String path;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {


        mineHead = (ImageView) findViewById(R.id.mine_head);
        mineName = (TextView) findViewById(R.id.mine_name);
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        CacheUserManager.getInstance().registerLogin(this);
        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
        if(loginBean != null){
            mineHead.setImageResource(R.drawable.my_user_bg_icon);
            mineName.setText(loginBean.getResult().getName());
        } else{
            mineHead.setImageResource(R.drawable.my_user_default);
            mineName.setText("未登录");
        }

        mineHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 path = "/sdcard/DCIM/Camera/"+time();
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri uriForFile = FileProvider.getUriForFile(getActivity(), "com.example.gitproject", new File(path));
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uriForFile);
                startActivityForResult(intent,103);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 103){
            Glide.with(getActivity()).load(path).into(mineHead);
        }
    }

    private String time() {
        return "IMG_"+System.currentTimeMillis()+".jpg";
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {
        //跳转页面
        Bundle bundle = new Bundle();
        bundle.putInt("headImg",R.drawable.my_user_bg_icon);
        FrameArouter.getInstance().build(CommonConstant.USER_EXIT_PATH).with(bundle).navigation();

    }

    @Override
    public void onLoginChange(LoginBean loginBean) {
        if(loginBean != null && mineName!=null){
            mineHead.setImageResource(R.drawable.my_user_bg_icon);
            mineName.setText(loginBean.getResult().getName());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        CacheUserManager.getInstance().unRegisterLogin(this);
    }


}