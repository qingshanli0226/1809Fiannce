package com.example.a1809zg.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1809zg.MainActivity;
import com.example.a1809zg.R;
import com.example.frame.BaseActivity;
import com.example.net.bean.RegisterBean;

public class RegitsterActivity extends BaseActivity<IUserPresenter>  implements IUserView{


    private EditText etRegisterNumber;
    private EditText etRegisterName;
    private EditText etRegisterPwd;
    private EditText etRegisterPwdagain;
    private Button btnRegister;
    private String name,pwd;
    private String code;
    @Override
    public void Regiter(RegisterBean registerBean) {
        String result = registerBean.getResult();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
         code = registerBean.getCode();
        if (code.equals("200")){
            Intent intent = new Intent(RegitsterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regitster;
    }

    @Override
    protected void initPresenter() {
        mPresenter=new IUserPresenter(RegitsterActivity.this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        etRegisterNumber = findViewById(R.id.et_register_number);
        etRegisterName = findViewById(R.id.et_register_name);
        etRegisterPwd = findViewById(R.id.et_register_pwd);
        etRegisterPwdagain = findViewById(R.id.et_register_pwdagain);
        btnRegister = findViewById(R.id.btn_register);

         name = etRegisterName.getText().toString();
         pwd = etRegisterPwd.getText().toString();
        String pwd2 = etRegisterPwdagain.getText().toString();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name!=null&&pwd!=null&&pwd2!=null){
                    if (pwd2.equals(pwd)){
                        mPresenter.UserPresenterData(name,pwd);

                    }
                }
            }
        });
    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }
}