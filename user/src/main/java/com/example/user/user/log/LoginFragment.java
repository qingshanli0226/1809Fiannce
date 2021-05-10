package com.example.user.user.log;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.Squilts;
import com.example.common.UserCallBack;
import com.example.framwork.base.BaseFragment;
import com.example.framwork.call.FiannceARouter;
import com.example.framwork.call.FiannceUserManager;
import com.example.framwork.view.TobView;
import com.example.network.model.LogBean;
import com.example.user.R;


public class LoginFragment extends BaseFragment<LogPresenter> implements LogCallBack {
    private TobView tob;
    private EditText logPhone;
    private EditText logUse;
    private EditText logPwd;
    private EditText logPhoneTwo;
    private Button log;
    private String user;
    private String pwd;
    private String phone;
    private String phonetwo;

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
        mPresenter=new LogPresenter(this);
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
                mPresenter.LogData(user,pwd);
            }
        });

    }

    @Override
    protected void initView() {

        tob =baseView.findViewById(R.id.tob);
        logPhone =baseView.findViewById(R.id.log_phone);
        logUse =baseView.findViewById(R.id.log_use);
        logPwd =baseView.findViewById(R.id.log_pwd);
        logPhoneTwo =baseView.findViewById(R.id.log_phone_two);
        log = baseView.findViewById(R.id.log);


    }

    @Override
    protected int FindLayout1() {
        return R.layout.main_log;
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
}
