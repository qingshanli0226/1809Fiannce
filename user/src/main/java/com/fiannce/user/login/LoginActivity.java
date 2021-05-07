package com.fiannce.user.login;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.SpUtiles;
import com.fiannce.framework.view.ToolBar;
import com.fiannce.net.mode.AutoLoginBean;
import com.fiannce.net.mode.LoginBean;
import com.fiannce.net.mode.UserBean;
import com.fiannce.user.R;
import com.fiannce.user.autologin.AutoLoginPresenter;
import com.fiannce.user.autologin.AutoLoginService;
import com.fiannce.user.autologin.IAutoLoginView;

import org.greenrobot.eventbus.EventBus;

@Route(path = "/login/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {


    private com.fiannce.framework.view.ToolBar toobarFake;
    private android.widget.EditText number;
    private android.widget.EditText e3;
    private android.widget.Button loginButton;

    @Override
    protected void initView() {

        toobarFake = (ToolBar) findViewById(R.id.toobar_fake);
        number = (EditText) findViewById(R.id.number);
        e3 = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        toobarFake.setToolbarListener(this);
    }

    @Override
    protected void initPresenter() {
        httppresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    httppresenter.getLogin(number.getText().toString(),e3.getText().toString());
            }
        });
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_login;
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

    @Override
    public void getLogin(LoginBean loginBean) {
        if (loginBean.getCode().equals("200")){
            UserBean userBean = new UserBean();
            userBean.setName(""+number.getText().toString());
            userBean.setPossword(""+e3.getText().toString());
            EventBus.getDefault().post(userBean);

            SharedPreferences sharedPreferences = getSharedPreferences("login.txt", MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("boo",true);
            edit.putString("name",number.getText().toString());
            edit.putString("pass",e3.getText().toString());
            edit.commit();

            SpUtiles.putString(this,loginBean.getResult().getToken());

            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build("/main/MainActivity").withInt("",0).navigation();
        }else {
            Toast.makeText(this, ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}
