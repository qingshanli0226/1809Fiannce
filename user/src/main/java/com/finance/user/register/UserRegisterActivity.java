package com.finance.user.register;

import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.finance.framework.BaseActivity;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.RegisterBean;
import com.finance.net.bean.UserBean;
import com.finance.user.R;
import com.finance.user.register.mvp.IRegisterView;
import com.finance.user.register.mvp.RegisterPresenter;

import org.greenrobot.eventbus.EventBus;


public class UserRegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView {

    private android.widget.EditText telEt;
    private android.widget.EditText UserNameEt;
    private android.widget.EditText PwdEt;
    private android.widget.EditText PwdAgainEt;
    private android.widget.Button registerBt;
    private SharedPreferences user;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_register;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new RegisterPresenter(this);
    }

    @Override
    protected void initData() {
        user = getSharedPreferences("user", MODE_PRIVATE);
        registerBt.setOnClickListener(v->{
            String userEt = UserNameEt.getText().toString().trim();
            String pwdEt = PwdEt.getText().toString().trim();
            if (userEt==null||pwdEt==null){
                Toast.makeText(this, "填写信息不能为空", Toast.LENGTH_SHORT).show();
            }else {
                httpPresenter.getRegisterData(userEt,pwdEt);
            }
        });
    }

    @Override
    protected void initView() {

        telEt = (EditText) findViewById(R.id.telEt);
        UserNameEt = (EditText) findViewById(R.id.UserNameEt);
        PwdEt = (EditText) findViewById(R.id.PwdEt);
        PwdAgainEt = (EditText) findViewById(R.id.PwdAgainEt);
        registerBt = (Button) findViewById(R.id.registerBt);
    }

    @Override
    public void onRegisterData(RegisterBean registerBean) {
        LogUtils.json(registerBean);
        if (registerBean.getCode().equals("200")){
            ARouter.getInstance().build("/main/MainActivity").withInt("",1).navigation();
        }else {
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLogin(LoginBean loginBean) {

    }

    @Override
    public void onAutoLogin(LoginBean loginBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }
    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}