package com.example.a1809fiannce.money;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a1809fiannce.CallImgPath;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.usermessage.MessageActivity;
import com.example.a1809fiannce.view.LinView;
import com.example.common.UserCallBack;
import com.example.framwork.base.BaseFragment;
import com.example.framwork.call.FiannceUserManager;
import com.example.framwork.view.TobView;
import com.example.network.model.LogBean;


public class MyMoneyFragment extends BaseFragment implements FiannceUserManager.IUserLoginChanged {
    private TextView user;
    private LinView investManage;
    private LinView investManageDia;
    private LinView assetsManage;
    private ImageView manyImg;

    @Override
    protected void initData() {
             tobView.setImgCallBackListener(new TobView.iImgCallBack() {
                 @Override
                 public void OnLeftImgListener() {

                 }

                 @Override
                 public void OnRightImgListener() {
                        startActivity(new Intent(getContext(), MessageActivity.class));
                 }
             });

        if (UserCallBack.getInstance().getName()!=null){
            user.setText(UserCallBack.getInstance().getName()+"");
        }
        investManage.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {

            }
        });
        investManageDia.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {

            }
        });
        assetsManage.setiRightImgCallBack(new LinView.iRightImgCallBack() {
            @Override
            public void OnRightListener() {

            }
        });

    }

    @Override
    protected void initView() {
        user = baseView.findViewById(R.id.user);
        investManage = baseView.findViewById(R.id.investManage);
        investManageDia =baseView.findViewById(R.id.investManageDia);
        assetsManage =baseView.findViewById(R.id.assetsManage);
        manyImg = baseView.findViewById(R.id.many_img);
        FiannceUserManager.getInstance().Register(this);
        if (CallImgPath.getInstance().getPath()!=null){
            Glide.with(getContext()).load(CallImgPath.getInstance().getPath()).into(manyImg);
        }
    }

    @Override
    protected int FindLayout1() {
        return R.layout.fragment_my_money;
    }

    @Override
    public void onLoginChange(LogBean isLog) {
        if (isLog!=null){
            Toast.makeText(getContext(), "已登录", Toast.LENGTH_SHORT).show();
            user.setText(isLog.getResult().getName());
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