package com.example.designed.fragment;


import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.designed.InActivity;
import com.example.designed.R;
import com.example.designed.demo.PayDemoActivity;
import com.example.user.login.LoginActivity;
import com.fiannce.bawei.framework.BaseFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends BaseFragment  {

    private ImageView setting;
    private ImageView pic;
    private TextView moery;
    private TextView withdraw;
    private LinearLayout invest;
    private LinearLayout manage;
    private LinearLayout property;
    private String path="";

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setting = (ImageView) findViewById(R.id.setting);
        pic = (ImageView) findViewById(R.id.pic);
        moery = (TextView) findViewById(R.id.moery);
        withdraw = (TextView) findViewById(R.id.withdraw);
        invest = (LinearLayout) findViewById(R.id.invest);
        manage = (LinearLayout) findViewById(R.id.manage);
        property = (LinearLayout) findViewById(R.id.property);

        moery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PayDemoActivity.class));
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), InActivity.class));
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                path = "/sdcard/DCIM/Camera/wang.jpg";

                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri uri = FileProvider.getUriForFile(getActivity(), "com.example.designed", new File(path));

                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent,103);




            }
        });
    }



    @Override
    protected int getLoutId() {
        return R.layout.fragment_blank_fragment3;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Glide.with(getActivity()).load(path).into(pic);
    }
}
