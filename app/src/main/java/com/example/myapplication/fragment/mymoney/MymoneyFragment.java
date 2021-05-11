
package com.example.myapplication.fragment.mymoney;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.demo.Demo.AROUTE_PATH_EXIT_LOGIN;

public class MymoneyFragment extends BaseFragment {


    private ToolBar toolbar;
    private TextView userName;
    private ImageView camera;
    private String path;

    public String capturename() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd hhmmss");
        String format = simpleDateFormat.format(date);
        return "IMG_" + format + ".jpg";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            Glide.with(this).load(path).transform(new CircleCrop()).into(camera);
        }
    }

    @Override
    protected void initData() {

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                path = "/sdcard/DCIM/Camera/" + capturename();
                Uri uri = FileProvider.getUriForFile(getActivity(), "com.example.myapplication", new File(path));

                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 101);
            }
        });

        EventBus.getDefault().register(this);

        boolean login1 = FiannceUserManager.getInstance().isLogin();
        if (login1){
            SharedPreferences login = getActivity().getSharedPreferences("login", 0);
            String name = login.getString("username", "");
            userName.setText("" + name);
        }else {
            userName.setText("请登录");
        }


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
    public void Events(String event) {
        if (event.equals("exit_login")) {
            userName.setText("");
            FiannceUserManager.getInstance().setLogin(false);
        }
    }

    @Subscribe(sticky = true)
    public void Svents(String event) {
        if (event.equals("success_login")) {
            SharedPreferences login = getActivity().getSharedPreferences("login", 0);
            String name = login.getString("username", "");
            userName.setText("" + name);
            FiannceUserManager.getInstance().setLogin(true);
        }
    }

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) mView.findViewById(R.id.toolbar);
        userName = (TextView) mView.findViewById(R.id.user_name);
        camera = (ImageView) mView.findViewById(R.id.camera);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS
            }, 100);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mymoney;
    }


}