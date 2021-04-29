package com.example.a1809fiannce.money;

import android.widget.TextView;
import android.widget.Toast;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.mian.UserCallBack;
import com.example.framwork.base.BaseFragment;
import com.example.framwork.call.FiannceUserManager;
import com.example.framwork.view.TobView;




public class MyMoneyFragment extends BaseFragment implements FiannceUserManager.IUserLoginChanged {
    private TextView user;

    @Override
    protected void initData() {
             tobView.setImgCallBackListener(new TobView.iImgCallBack() {
                 @Override
                 public void OnLeftImgListener() {
                     Toast.makeText(getContext(), "这是图片", Toast.LENGTH_SHORT).show();
                 }

                 @Override
                 public void OnRightImgListener() {
                     Toast.makeText(getContext(), "这是图片", Toast.LENGTH_SHORT).show();
                 }
             });

        if (UserCallBack.getInstance().getName()!=null){
            user.setText(UserCallBack.getInstance().getName()+"");
        }
    }

    @Override
    protected void initView() {
        user = baseView.findViewById(R.id.user);
            FiannceUserManager.getInstance().Register(this);
            if (FiannceUserManager.getInstance().getLog()){
                Toast.makeText(getContext(), "当前用户已经登录", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "当前用户未登录", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    protected int FindLayout1() {
        return R.layout.fragment_my_money;
    }

    @Override
    public void onLoginChange(boolean isLog) {
        if (isLog){
            Toast.makeText(getContext(), "已登录", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "未登录", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        FiannceUserManager.getInstance().UnRegister(this);

    }
}