package com.example.user.reg;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framwork.base.BaseActivity;
import com.example.framwork.view.TobView;
import com.example.network.model.RegBean;
import com.example.user.log.LogActivity;
import com.example.user.R;

public class RegisterActivity extends BaseActivity<RegPresenter> implements RegisterCallBack {

    private EditText reusePhone;
    private String reuse;
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
                phoneNum=phone.getText().toString();
                name=use.getText().toString();
                password=pwd.getText().toString();
                reuse=reusePhone.getText().toString();
                if (phoneNum.equals("")&&name.equals("")&&password.equals("")&&reuse.equals("")){
                    Toast.makeText(RegisterActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.RegData(name,password);
            }
        });
    }

    @Override
    protected void initView() {
        tob = (TobView) findViewById(R.id.tob);
        phone = (EditText) findViewById(R.id.phone);
        use = (EditText) findViewById(R.id.use);
        pwd = (EditText) findViewById(R.id.pwd);
        reusePhone = (EditText) findViewById(R.id.reuse_phone);
        register = (Button) findViewById(R.id.register);
    }

    @Override
    protected int FindLayout1() {
        return R.layout.activity_register;
    }

    @Override
    public void RegData(RegBean regBean) {
        Toast.makeText(this, ""+regBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (regBean.getCode().equals("200")){
            Toast.makeText(this, ""+regBean.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LogActivity.class);
            intent.putExtra("name",name);
            intent.putExtra("pwd",password);
            startActivity(intent);
        }else {
            Toast.makeText(this, ""+regBean.getMessage(), Toast.LENGTH_SHORT).show();

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