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
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {

        CacheUserManager.getInstance().registerLogin(this);
        LoginBean cache = CacheUserManager.getInstance().getLoginBean();
        isFlag = isGPassword(cache);


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
                ImageView rightItemPic = itemPwd.getRightItemPic();
                if(isLogin){
                    if(isFlag){
                        rightItemPic.setImageResource(R.drawable.toggle_off);
                        isFlag = false;
                    } else{
                        rightItemPic.setImageResource(R.drawable.toggle_on);
                        isFlag = true;
                        HashMap<String, String> stringStringHashMap = new HashMap<>();
                        stringStringHashMap.put("gPassword","123");
                        String s = new Gson().toJson(stringStringHashMap);
                        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
                        RequestBody requestBody = RequestBody.create(parse, s);
                        RetrofitManager.getHttpApiService().setGesturePassword(requestBody)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<GesturePassword>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onNext(@NonNull GesturePassword gesturePassword) {
                                        Toast.makeText(getActivity(), ""+gesturePassword, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Toast.makeText(getActivity(), "aaaa", Toast.LENGTH_SHORT).show();
                                        Log.i("TAG", "onError: "+e.getMessage());
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
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
        isFlag = isGPassword(loginBean);

    }

    private boolean isGPassword(LoginBean loginBean) {
        if(loginBean == null){
            isLogin = false;
            return false;
        }
        isLogin = true;
        if(loginBean.getResult() != null){
            if(loginBean.getResult().getgPassword() != null){
                return false;
            }
        }
        return true;
    }
}