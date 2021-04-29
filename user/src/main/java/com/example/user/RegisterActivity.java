package com.example.user;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.user.mvp.MorePresenter;
import com.example.user.mvp.MoreView;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;

//@Route(path = "/mare/register")
public class RegisterActivity extends BaseActivity<MorePresenter> implements MoreView {
    private android.widget.EditText phone;
    private android.widget.EditText name;
    private android.widget.EditText pwd;
    private android.widget.EditText confirmPwd;
    private android.widget.Button regbtn;
    private String userName;
    private String userPwd;

    @Override
    protected int getbandLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        phone = (EditText) findViewById(R.id.phone);
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
        confirmPwd = (EditText) findViewById(R.id.confirmPwd);
        regbtn = (Button) findViewById(R.id.regbtn);
    }

    @Override
    protected void initData() {
        //获取输入的用户名与密码
        regbtn.setOnClickListener(v -> {
             userName = name.getText().toString().trim();
             userPwd = pwd.getText().toString().trim();
            if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(userPwd)){
                Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            mPresenter.getRegister(userName,userPwd);
            name.setText("");
            pwd.setText("");
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter=new MorePresenter(this);
    }

    @Override
    public void initRegister(RegisterBean registerBean) {
        String code = registerBean.getCode();
        if (code.equals("200")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            //注册成功的话就登陆
            MorePresenter morePresenter = new MorePresenter(this);
            morePresenter.getLogin(userName,userPwd);
        }else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void initLogin(LoginBean loginBean) {
        Toast.makeText(this, "login:"+loginBean.toString(), Toast.LENGTH_SHORT).show();

    }
}