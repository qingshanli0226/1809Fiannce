package com.example.user.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.net.ToolBarView;
import com.example.net.mode.RegBean;
import com.example.user.R;

public class RegisterFragment extends BaseFragment<RegPresenter> implements RegisterCallBack {

    private EditText reusePhone;
    private String reuse;
    private ToolBarView tob;
    private EditText phone;
    private EditText use;
    private EditText pwd;
    private Button register;
    private String phoneNum;
    private String name;
    private String password;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        httpPresenter=new RegPresenter(this);
        phoneNum = phone.getText().toString();
        name = use.getText().toString();
        password = pwd.getText().toString();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum=phone.getText().toString();
                name=use.getText().toString();
                password=pwd.getText().toString();
                reuse=reusePhone.getText().toString();
                if (phoneNum.equals("")&&name.equals("")&&password.equals("")&&reuse.equals("")){
                    Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                httpPresenter.RegData(name,password);
            }
        });
    }

    @Override
    protected void initView() {
        tob = (ToolBarView) mView.findViewById(R.id.tob);
        phone = (EditText) mView.findViewById(R.id.phone);
        use = (EditText) mView.findViewById(R.id.use);
        pwd = (EditText) mView.findViewById(R.id.pwd);
        reusePhone = (EditText) mView.findViewById(R.id.reuse_phone);
        register = (Button) mView.findViewById(R.id.register);
    }

    @Override
    public void RegData(RegBean regBean) {
        if (regBean.getCode().equals("200")){
            Toast.makeText(getActivity(), ""+regBean.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction("log");
            getActivity().sendBroadcast(intent);
        }else {
            Toast.makeText(getActivity(), ""+regBean.getMessage(), Toast.LENGTH_SHORT).show();

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