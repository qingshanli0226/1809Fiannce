package com.example.user.register;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.net.model.RegisterBean;
import com.example.user.R;

@Route(path = "/user/LoginActivity")
public class RegisterActivity extends BaseActivity<RegisterPresneter> implements IRegisterView{


    private ToolBar toolbar;
    private TextView actRegPhone;
    private EditText actRegUsername;
    private EditText actRegPassword;
    private EditText actRegPasswordtwo;
    private TextView actRegBut;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        actRegBut.setOnClickListener(view -> {
            String user = actRegUsername.getText().toString().trim();
            String pwd = actRegPassword.getText().toString().trim();
            String pwdtwo = actRegPasswordtwo.getText().toString().trim();

            if (user.equals("")||pwd.equals("")){
                Toast.makeText(this,R.string.theUsernameAndPasswordCannotBeEmpty , Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pwd.equals(pwdtwo)) {
                Toast.makeText(this,R.string.theTwoPasswordsDontMatch , Toast.LENGTH_SHORT).show();
                return;
            }
            httpPresenter.getRegisterData(user,pwd);
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new RegisterPresneter(this);
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        actRegPhone = (TextView) findViewById(R.id.act_reg_phone);
        actRegUsername = (EditText) findViewById(R.id.act_reg_username);
        actRegPassword = (EditText) findViewById(R.id.act_reg_password);
        actRegPasswordtwo = (EditText) findViewById(R.id.act_reg_passwordtwo);
        actRegBut = (TextView) findViewById(R.id.act_reg_but);
    }

    @Override
    public void getRegisterData(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")){
            Toast.makeText(this, registerBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Error(String error) {

    }
}