package com.example.user.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.Squilts;
import com.example.common.UserCallBack;
import com.example.framework.BaseFragment;
import com.example.framework.FiannceARouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.net.ToolBarView;
import com.example.net.mode.LogBean;
import com.example.user.R;

public class LoginFragment extends BaseFragment<LogPresenter> implements LogCallBack {

    private EditText logPhone;
    private EditText logUse;
    private EditText logPwd;
    private EditText logPhoneTwo;

    private String user;
    private String pwd;
    private String phone;
    private String phonetwo;

    private Button log;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        httpPresenter = new LogPresenter(this);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = logUse.getText().toString();
                pwd = logPwd.getText().toString();
                phone = logPhone.getText().toString();
                phonetwo = logPhoneTwo.getText().toString();

                if (user.equals("")&&pwd.equals("")&&phone.equals("")&&phonetwo.equals("")){
                    Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                httpPresenter.LogData(user,pwd);
            }
        });
    }

    @Override
    protected void initView() {
        logPhone =mView.findViewById(R.id.log_phone);
        logUse =mView.findViewById(R.id.log_use);
        logPwd =mView.findViewById(R.id.log_pwd);
        logPhoneTwo =mView.findViewById(R.id.log_phone_two);
        log = (Button) mView.findViewById(R.id.log);
    }

    @Override
    public void LogData(LogBean logBean) {
        if (logBean.getCode().equals("200")){
            Toast.makeText(getContext(), ""+logBean.getMessage(), Toast.LENGTH_SHORT).show();
            FiannceUserManager.getInstance().setIsLog(logBean);

            UserCallBack.getInstance().setName(user);
            Squilts.putString(getContext(),logBean.getResult().getToken());
            FiannceARouter.getFiannceARouter().getAppManager().OpenMainActivity(getContext(),null);
        }else {
            Toast.makeText(getContext(), ""+logBean.getMessage(), Toast.LENGTH_SHORT).show();
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