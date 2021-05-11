package com.example.a1809fiannce.myAssets;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.a1809fiannce.R;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.mode.LoginBean;
import com.example.user.userinfo.UserInfoActivity;

import java.io.File;


public class MyAssetsFragment extends BaseFragment implements FiannceUserManager.IUserLoginChanged {
    private ToolBar toolbar;
    private ImageView HeadPortrait;
    private TextView username;
    private TextView topUp;
    private TextView withdraw;
    private String path;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myassets;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        HeadPortrait = (ImageView) findViewById(R.id.HeadPortrait);
        username = (TextView) findViewById(R.id.username);
        topUp = (TextView) findViewById(R.id.topUp);
        withdraw = (TextView) findViewById(R.id.withdraw);
    }

    @Override
    protected void initData() {
        FiannceUserManager.getInstance().registerUserLoginChanged(this);

        LoginBean loginBean = FiannceUserManager.getInstance().getLoginBean();
        if (loginBean!=null){
            username.setText(loginBean.getResult().getName());
        }

        HeadPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera();
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onRightImgClick() {
        super.onRightImgClick();
        FiannceArouter.getInstance().getIUserInterface().openUserInfoActivity(getContext(),null);

//        Intent intent = new Intent(getContext(), UserInfoActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onLoginChanged(LoginBean loginBean) {
        if (loginBean!=null){
            username.setText(loginBean.getResult().getName());
        }else {
            username.setText("Hi,welcome!");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        FiannceUserManager.getInstance().unregisterUserLoginChanged(this);
    }

    public void camera(){
        path="/sdcard/DCIM/Camera/aa.jpg";

        Intent intent = new Intent();

        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri uri= FileProvider.getUriForFile(getContext(),"com.example.a1809fiannce",new File(path));

        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,103);

        Glide.with(getContext()).load(path).transform(new CircleCrop()).into(HeadPortrait);
    }
}