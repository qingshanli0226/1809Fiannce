package com.example.gitproject.more;

import android.app.UiAutomation;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.common.CommonConstant;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.module.FrameArouter;
import com.example.framework.view.ItemBar;
import com.example.gitproject.R;
import com.example.gitproject.more.password.status.GestureStatus;
import com.example.net.RetrofitManager;
import com.example.net.bean.GesturePassword;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ProductBean;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class MoreFragment extends BaseFragment implements CacheUserManager.ILoginChange {


    private ItemBar itemRegister;
    private ItemBar itemPwd;
    private ItemBar itemResetPwd;
    private ItemBar itemPhone;
    private ItemBar itemAbs;
    private ItemBar itemSms;
    private ItemBar itemShare;
    private boolean isFlag = false;
    private boolean isLogin = false;
    private ImageView rightItemPic;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView() {

        itemRegister = (ItemBar) findViewById(R.id.item_register);
        itemPwd = (ItemBar) findViewById(R.id.item_pwd);
        itemResetPwd = (ItemBar) findViewById(R.id.item_reset_pwd);
        itemPhone = (ItemBar) findViewById(R.id.item_phone);
        itemAbs = (ItemBar) findViewById(R.id.item_abs);
        itemSms = (ItemBar) findViewById(R.id.item_sms);
        itemShare = (ItemBar) findViewById(R.id.item_share);
        rightItemPic = itemPwd.getRightItemPic();

    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {

        CacheUserManager.getInstance().registerLogin(this);
        LoginBean cache = CacheUserManager.getInstance().getLoginBean();
        isLogin = isGPassword(cache);


        //注册
        itemRegister.setItemOnClickLisenter(new ItemBar.ItemOnClickLisenter() {
            @Override
            public void leftOnClick() {
                //FrameArouter
                FrameArouter.getInstance().build(CommonConstant.USER_REGISTER_PATH).build(CommonConstant.USER_REGISTER_PATH).navigation();

            }

            @Override
            public void rightOnClick() {

            }

            @Override
            public void titleOnClick() {
                //FrameArouter
                FrameArouter.getInstance().build(CommonConstant.USER_REGISTER_PATH).build(CommonConstant.USER_REGISTER_PATH).navigation();

            }

            @Override
            public void rightTextOnClick() {

            }

            @Override
            public void itemOnClick() {
                //Arouter跳转
//                ARouter.getInstance().build(CommonConstant.USER_REGISTER_PATH).navigation();
                //接口回调
//                FrameArouter.getInstance().getUserInterface().onGoRegister(getActivity(),null);
                //FrameArouter
                FrameArouter.getInstance().build(CommonConstant.USER_REGISTER_PATH).build(CommonConstant.USER_REGISTER_PATH).navigation();
            }
        });


        //设置密码
        itemPwd.setItemOnClickLisenter(new ItemBar.ItemOnClickLisenter() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {
                if(isLogin){
                    if(isFlag){
                        rightItemPic.setImageResource(R.drawable.toggle_off);
                        isFlag = false;
                        GestureStatus.getInstance().setPwdStatus(CommonConstant.STATUS_CLEAR);
                        FrameArouter.getInstance().build(CommonConstant.APP_PWD_PATH).navigation();
                    } else{
                        rightItemPic.setImageResource(R.drawable.toggle_on);
                        isFlag = true;
                        GestureStatus.getInstance().setPwdStatus(CommonConstant.STATUS_SET);
                        FrameArouter.getInstance().build(CommonConstant.APP_PWD_PATH).navigation();
                    }
                }

            }

            @Override
            public void titleOnClick() {

            }

            @Override
            public void rightTextOnClick() {

            }

            @Override
            public void itemOnClick() {

            }
        });



    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }

    @Override
    public void onLoginChange(LoginBean loginBean) {
        isLogin = isGPassword(loginBean);

    }

    private boolean isGPassword(LoginBean loginBean) {
        if(loginBean != null){
            Object o = loginBean.getResult().getgPassword();
            if(o != null){
                rightItemPic.setImageResource(R.drawable.toggle_on);
                isFlag = true;
            } else{
                rightItemPic.setImageResource(R.drawable.toggle_off);
                isFlag = false;
            }
            return true;
        }
        return false;

    }

    @Override
    public void destroy() {
        super.destroy();
        CacheUserManager.getInstance().unRegisterLogin(this);
    }
}