package com.example.user.user.reg;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.framwork.base.BaseFragment;
import com.example.framwork.view.TobView;
import com.example.network.model.RegBean;
import com.example.user.R;
import com.example.user.user.reg.RegPresenter;
import com.example.user.user.reg.RegisterCallBack;

public class RegisterFragment extends BaseFragment<RegPresenter> implements RegisterCallBack {
    private EditText reusePhone;
    private String reuse;
    private TobView tob;
    private EditText phone;
    private EditText use;
    private EditText pwd;
    private Button register;
    private String phoneNum;
    private String name;
    private String password;

    @Override
    public void ShowLoading() {

    }

    @Override
    public void HideLoading() {

    }

    @Override
    public void Error(String error) {
        Log.i("zx", "Error: "+error);
    }

    @Override
    protected void initData() {
        mPresenter=new RegPresenter(this);
        phoneNum = phone.getText().toString();
        name = use.getText().toString();
        password = pwd.getText().toString();

        tob.setImgCallBackListener(new TobView.iImgCallBack() {
            @Override
            public void OnLeftImgListener() {
                getActivity().finish();
            }

            @Override
            public void OnRightImgListener() {

            }
        });

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
                mPresenter.RegData(name,password);
            }
        });
    }

    @Override
    protected void initView() {
        tob = (TobView) baseView.findViewById(R.id.tob);
        phone = (EditText) baseView.findViewById(R.id.phone);
        use = (EditText) baseView.findViewById(R.id.use);
        pwd = (EditText) baseView.findViewById(R.id.pwd);
        reusePhone = (EditText) baseView.findViewById(R.id.reuse_phone);
        register = (Button) baseView.findViewById(R.id.register);
    }

    @Override
    protected int FindLayout1() {
        return R.layout.activity_register;
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
}
