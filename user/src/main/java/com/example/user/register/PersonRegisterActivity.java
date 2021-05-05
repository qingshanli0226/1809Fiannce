package com.example.user.register;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.example.demo.Demo;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.model.HomeBean;
import com.example.model.LoginBean;
import com.example.model.ProductBean;
import com.example.model.RegisterBean;
import com.example.model.VersionBean;
import com.example.user.R;

import org.greenrobot.eventbus.EventBus;

@Route(path = Demo.AROUTE_PATH_REGISTER)
public class PersonRegisterActivity extends BaseActivity<PersonRegisterPresenter> implements IPersonRegisterView {


    private ToolBar toolbar;
    private EditText registerName;
    private EditText registerPwd;
    private EditText registerConfimPwd;
    private Button register;

    private String username;
    private String password;

    @Override
    protected void initData() {


        toolbar.setToolbarListener(new ToolBar.IToolbarListener() {
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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 username = registerName.getText().toString().trim();
                 password = registerPwd.getText().toString().trim();
                String confimpwd = registerConfimPwd.getText().toString().trim();
//                Toast.makeText(PersonRegisterActivity.this, "123", Toast.LENGTH_SHORT).show();


                if (confimpwd.equals(password)){
                    httpPresenter.postRegister(username,password);

                }else {
                    Toast.makeText(PersonRegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new PersonRegisterPresenter(this);
    }

    @Override
    protected void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        registerName = (EditText) findViewById(R.id.register_name);
        registerPwd = (EditText) findViewById(R.id.register_pwd);
        registerConfimPwd = (EditText) findViewById(R.id.cregister_confim_pwd);
        register = (Button) findViewById(R.id.register);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_register;
    }



    @Override
    public void onRegister(RegisterBean registerBean) {
        LogUtils.json(registerBean);

        String code = registerBean.getCode();
        if (code.equals("200")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().postSticky("home_data");

        }

    }


    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onConnected() {
        super.onConnected();
        Toast.makeText(this, "网络已连接", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        super.onDisconnected();
        Toast.makeText(this, "网络未连接", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }
}