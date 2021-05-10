package com.example.user;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.FiannceConstants;
import com.example.common.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.mannager.FiannceUserMannager;
import com.example.net.bean.AutoBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.LogoutBean;
import com.example.net.bean.RegisterBean;
import com.example.user.mvp.MorePresenter;
import com.example.user.mvp.MoreView;

public class UserMessageActivity extends BaseActivity<MorePresenter> implements MoreView {

    private ImageView userMessageImage;
    private TextView changeImage;
    private Button logOut;

    @Override
    protected int getbandLayout() {
        return R.layout.activity_user_message;
    }

    @Override
    protected void initView() {
        userMessageImage = (ImageView) findViewById(R.id.userMessageImage);
        changeImage = (TextView) findViewById(R.id.changeImage);
        logOut = (Button) findViewById(R.id.logOut);
    }

    @Override
    protected void initData() {
        //退出登录
        logOut.setOnClickListener(v -> {
            Toast.makeText(this, SpUtil.getString(this, FiannceConstants.TOKEN_KEY), Toast.LENGTH_SHORT).show();
            mPresenter.onUnLogin(SpUtil.getString(this, FiannceConstants.TOKEN_KEY));
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter=new MorePresenter(this);
    }

    @Override
    public void initRegister(RegisterBean registerBean) {

    }

    @Override
    public void initLogin(LoginBean loginBean) {

    }

    @Override
    public void initAuto(AutoBean autoBean) {

    }

    @Override
    public void initLogout(LogoutBean logoutBean) {
        //退出登陆
        String code = logoutBean.getCode();
        if (code.equals("200")){
            Toast.makeText(this, "退出登陆成功", Toast.LENGTH_SHORT).show();
            SpUtil.setString(this,"");
            finish();
        }else {
            Toast.makeText(this, "退出登陆失败", Toast.LENGTH_SHORT).show();
        }
    }
}