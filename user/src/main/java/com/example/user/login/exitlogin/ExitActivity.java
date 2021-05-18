package com.example.user.login.exitlogin;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.demo.Demo;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FinanceUserManager;
import com.example.framework.view.ToolBar;
import com.example.model.ExitLoginBean;
import com.example.sp.SpUtils;
import com.example.user.R;

import org.greenrobot.eventbus.EventBus;

@Route(path = Demo.AROUTE_PATH_EXIT_LOGIN)
public class ExitActivity extends BaseActivity<ExitLoginPresenter> implements IExitLoginView {

    private ToolBar toolbar;
    private android.widget.Button exitLogin;

    @Override
    protected void initData() {

        toolBar.setToolbarListener(new ToolBar.IToolbarListener() {
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
        });


        exitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                httpPresenter.exitLogin();



                finish();
            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ExitLoginPresenter(this);
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        exitLogin = (Button) findViewById(R.id.exit_login);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exit;
    }

    @Override
    public void onExitLoginData(ExitLoginBean exitLoginBean) {
        if (exitLoginBean.getCode().equals("200")){
            Toast.makeText(this, "退出成功sss", Toast.LENGTH_SHORT).show();
            FinanceUserManager.getInstance().setLogin(false);
//                String string = SpUtils.getString(ExitActivity.this);
            SpUtils.putString(ExitActivity.this,"");

            boolean login = FinanceUserManager.getInstance().isLogin();
            if (login==false){
                Toast.makeText(ExitActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                SharedPreferences login1 = getSharedPreferences("login", 0);
                SharedPreferences.Editor edit = login1.edit();
                edit.putBoolean("is_login",false);
                edit.commit();
            }
            EventBus.getDefault().postSticky("exit_login");
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