package com.example.a1809fiannce.many.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.mian.MainActivity2;
import com.example.framwork.base.BaseActivity;
import com.example.framwork.view.TobView;
import com.example.network.model.RegBean;

public class RegisterActivity extends BaseActivity<RegPresenter> implements RegisterCallBack{
    private TobView tob;
    private EditText phone;
    private EditText use;
    private EditText pwd;
    private Button register;
    private String phoneNum;
    private String name;
    private String password;

    @Override
    protected void initData() {
        mPresenter=new RegPresenter(this);
        phoneNum = phone.getText().toString();
        name = use.getText().toString();
        password = pwd.getText().toString();

        tob.setImgCallBackListener(new TobView.iImgCallBack() {
            @Override
            public void OnLeftImgListener() {
                finish();
            }

            @Override
            public void OnRightImgListener() {

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "111", Toast.LENGTH_SHORT).show();
                Log.i("zx", "RegData: phoneNum="+phone+"name="+name+"password="+password);
                if (phoneNum.equals("")&&name.equals("")&&password.equals("")){
                    Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.RegData("aaa","1223");
            }
        });
    }

    @Override
    protected void initView() {
        tob = (TobView) findViewById(R.id.tob);
        phone = (EditText) findViewById(R.id.phone);
        use = (EditText) findViewById(R.id.use);
        pwd = (EditText) findViewById(R.id.pwd);
        register = (Button) findViewById(R.id.register);
    }

    @Override
    protected int FindLayout1() {
        return R.layout.activity_us;
    }

    @Override
    public void RegData(RegBean regBean) {
        Log.i("zx", "RegData: "+regBean.toString());
        Toast.makeText(this, ""+regBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (regBean.getCode().equals("200")){
            Intent intent = new Intent(RegisterActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void ShowLoading() {

    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void Error(String error) {
        Log.i("zx", "Error: "+error);
    }


}