package com.example.a1809fiannce.usermessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1809fiannce.R;
import com.example.common.Squilts;
import com.example.common.UserCallBack;
import com.example.framwork.call.FiannceARouter;
import com.example.framwork.call.FiannceUserManager;
import com.example.framwork.view.TobView;

public class MessageActivity extends AppCompatActivity {
    private TobView tob;
    private ImageView messImg;
    private TextView head;
    private Button out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        tob = (TobView) findViewById(R.id.tob);
        messImg = (ImageView) findViewById(R.id.mess_img);
        head = (TextView) findViewById(R.id.head);
        out = (Button) findViewById(R.id.out);

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
    }
}