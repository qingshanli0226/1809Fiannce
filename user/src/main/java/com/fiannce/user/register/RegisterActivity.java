package com.fiannce.user.register;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseActivity;
import com.fiannce.framework.view.ToolBar;
import com.fiannce.net.mode.RegisterBean;
import com.fiannce.user.R;

@Route(path = "/user/RegisterActivity")
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegisterView{


    private com.fiannce.framework.view.ToolBar toobarFake;
    private android.widget.EditText e1;
    private android.widget.EditText e2;
    private android.widget.EditText e3;
    private android.widget.EditText e4;
    private android.widget.Button registerButton;

    @Override
    protected void initView() {

        toobarFake = (ToolBar) findViewById(R.id.toobar_fake);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);
        registerButton = (Button) findViewById(R.id.register_button);
        toobarFake.setToolbarListener(this);
    }

    @Override
    protected void initPresenter() {
        httppresenter = new RegisterPresenter(this);
    }

    @Override
    protected void initData() {

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e1.getText().length() == 0||e2.getText().length() == 0||e3.getText().length() == 0||e4.getText().length() == 0){
                    Toast.makeText(RegisterActivity.this, "填写信息不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    httppresenter.getRegister(e2.getText().toString(),e3.getText().toString());
                    if (e3.getText().toString().equals(e4.getText().toString())){

                    }else {
                        e3.setText("");
                        e4.setText("");
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutid() {
        return R.layout.activity_register;
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
    public void onRegister(RegisterBean registerBean) {
        if (registerBean.getCode().equals("200")){
            String result = registerBean.getResult();
            Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build("/login/LoginActivity").withInt("",3).navigation();
        }else {
            String result = registerBean.getResult();
            Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
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
