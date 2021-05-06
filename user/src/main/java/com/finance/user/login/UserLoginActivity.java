package com.finance.user.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.finance.framework.BaseActivity;
import com.finance.framework.manager.CacheManager;
import com.finance.framework.manager.CacheUserManager;
import com.finance.framework.sp.Constant;
import com.finance.framework.sp.SPUtil;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.UserBean;
import com.finance.user.R;
import com.finance.user.login.mvp.ILoginView;
import com.finance.user.login.mvp.LoginPresenter;

import org.greenrobot.eventbus.EventBus;

public class UserLoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {
    private android.widget.EditText loginUserNameEt;
    private android.widget.EditText loginPwdEt;
    private android.widget.Button loginBt;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String logUser = loginUserNameEt.getText().toString().trim();
                String logPwd = loginPwdEt.getText().toString().trim();
                if (logUser==null||logPwd==null){
                    Toast.makeText(UserLoginActivity.this, "填写信息不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    httpPresenter.getLoginData(logUser,logPwd);
                }
            }
        });

    }

    @Override
    public void onLoginData(LoginBean loginBean) {
        LogUtils.json(loginBean);
        if (loginBean.getCode().equals("200")){
            SPUtil.putString(this, Constant.SP_TOKEN,loginBean.getResult().getToken());
            //跳到主页面返回
            CacheUserManager.getInstance().setLoginBean(loginBean);
            ARouter.getInstance().build("/main/MainActivity").withInt("",1).navigation();
        }else {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initView() {

        loginUserNameEt = (EditText) findViewById(R.id.login_UserNameEt);
        loginPwdEt = (EditText) findViewById(R.id.login_PwdEt);
        loginBt = (Button) findViewById(R.id.loginBt);
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

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }

}
