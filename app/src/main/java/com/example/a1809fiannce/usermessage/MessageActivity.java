package com.example.a1809fiannce.usermessage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.a1809fiannce.R;
import com.example.common.Squilts;
import com.example.common.UserCallBack;
import com.example.framwork.call.FiannceARouter;
import com.example.framwork.call.FiannceUserManager;
import com.example.framwork.view.TobView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageActivity extends AppCompatActivity {
    private TobView tob;
    private ImageView messImg;
    private TextView head;
    private Button out;
    private String path="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
            },100);
        }

        tob = findViewById(R.id.tob);
        messImg = findViewById(R.id.mess_img);
        head = findViewById(R.id.head);
        out = findViewById(R.id.out);

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiannceUserManager.getInstance().setIsLog(null);
                UserCallBack.getInstance().setName(null);
                Squilts.putString(MessageActivity.this,null);
                FiannceARouter.getFiannceARouter().getAppManager().OpenMainActivity(MessageActivity.this,null);
            }
        });
        tob.setImgCallBackListener(new TobView.iImgCallBack() {
            @Override
            public void OnLeftImgListener() {
                finish();
            }

            @Override
            public void OnRightImgListener() {

            }
        });
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                path="/sdcard/DCIM/Camera/"+CreatName();
                Uri uri = FileProvider.getUriForFile(MessageActivity.this, "com.example.a1809fiannce", new File(path));
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent,104);

            }
        });

    }
    private String CreatName() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_mmss");
        String format = simpleDateFormat.format(date);
        return "IMG_"+format+".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==104&&resultCode== Activity.RESULT_OK){
            SharedPreferences sharedPreferences = getSharedPreferences("imgPath", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("path",path);
            edit.commit();
            Glide.with(this)
                   .load(path)
                   .transform(new CircleCrop())
                   .into(messImg);
        }
    }
}