package com.finance.zg6.main.myassets;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.finance.framework.manager.CacheUserManager;
import com.finance.framework.sp.Constant;
import com.finance.framework.sp.SPUtil;
import com.finance.zg.R;
import com.finance.zg6.main.MainActivity;

public class ProlifePicActivity extends AppCompatActivity {

    private ImageView userImg;
    private TextView profilePic;
    private Button exitLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prolife_pic);

        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        int img = intent.getIntExtra("img",0);
        userImg.setImageResource(img);
        exitLogin.setOnClickListener(v->{
            CacheUserManager.getInstance().unRegisterLogin(null);
            SPUtil.putString(this, Constant.SP_TOKEN,"");
            Intent intent1 = new Intent(ProlifePicActivity.this, MainActivity.class);
            startActivity(intent1);
        });
    }


    private void initView() {
        userImg = (ImageView) findViewById(R.id.userImg);
        profilePic = (TextView) findViewById(R.id.profilePic);
        exitLogin = (Button) findViewById(R.id.exitLogin);
    }
}