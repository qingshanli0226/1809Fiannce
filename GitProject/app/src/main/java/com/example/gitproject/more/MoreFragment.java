package com.example.gitproject.more;

import android.app.UiAutomation;
import android.widget.ImageView;

import com.example.common.CommonConstant;
import com.example.framework.BaseFragment;
import com.example.framework.module.FrameArouter;
import com.example.framework.view.ItemBar;
import com.example.gitproject.R;


public class MoreFragment extends BaseFragment {


    private ItemBar itemRegister;
    private ItemBar itemPwd;
    private ItemBar itemResetPwd;
    private ItemBar itemPhone;
    private ItemBar itemAbs;
    private ItemBar itemSms;
    private ItemBar itemShare;
    private boolean isFlag = false;

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
                if(isFlag){
                    rightItemPic.setImageResource(R.drawable.toggle_off);
                    isFlag = false;
                } else{
                    rightItemPic.setImageResource(R.drawable.toggle_on);
                    isFlag = true;
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
}