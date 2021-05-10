package com.example.user.usermessage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.commom.FianceConstants;
import com.example.commom.SpUtil;
import com.example.framework.BaseActivity;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.model.UnlockBean;
import com.example.user.R;

public class UserActivity extends BaseActivity<UserPresenter> implements IUserView{

    private TextView actUserBacklogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initData() {
        actUserBacklogin.setOnClickListener(view -> {
            httpPresenter.getUnlockData();
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UserPresenter(this);
    }

    @Override
    public void onLeftImgClick() {
        super.onLeftImgClick();
        finish();
    }

    @Override
    protected void initView() {
        actUserBacklogin = (TextView) findViewById(R.id.act_user_backlogin);
    }

    @Override
    public void onUserData(UnlockBean unlockBean) {
        if (unlockBean.getCode().equals("200")) {
            Toast.makeText(this, R.string.backLoginyes, Toast.LENGTH_SHORT).show();
            FiannceUserManager.getInstance().setLoginBean(null);
            SpUtil.setString(this, FianceConstants.TOKEN_KEY,"");
            FiannceArouter.getInstance().build(FianceConstants.MAIN_PATH).navigation();
            finish();
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