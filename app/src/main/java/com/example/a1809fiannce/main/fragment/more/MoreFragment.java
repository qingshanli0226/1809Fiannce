package com.example.a1809fiannce.main.fragment.more;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.a1809fiannce.R;
import com.example.a1809fiannce.main.fragment.more.adapter.MyFragmentAdapter;
import com.fiannce.bawei.framework.BaseFragmnet;
import com.fiannce.bawei.user.UserModule;
import com.fiannce.bawei.user.regiseter.RegisterActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragmnet {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private TabLayout moneyTab;
    private ViewPager moneyVp;
    private MyFragmentAdapter adapter;
    private LinearLayout nameRegister;
    private LinearLayout gesturePassword;
    private LinearLayout ResetTheGesturePassword;
    private LinearLayout contactTheCustomerService;
    private LinearLayout userFeedback;
    private LinearLayout FriendsShare;
    private LinearLayout AboutYoudaoMoneyManagement;

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        nameRegister = (LinearLayout) mView.findViewById(R.id.name_register);
        gesturePassword = (LinearLayout) mView.findViewById(R.id.gesture_password);
        ResetTheGesturePassword = (LinearLayout) mView.findViewById(R.id.Reset_the_gesture_password);
        contactTheCustomerService = (LinearLayout)mView. findViewById(R.id.contact_the_customer_service);
        userFeedback = (LinearLayout) mView.findViewById(R.id.user_feedback);
        FriendsShare = (LinearLayout) mView.findViewById(R.id.Friends_share);
        AboutYoudaoMoneyManagement = (LinearLayout) mView.findViewById(R.id.About_youdao_money_management);
        onClinK();
    }

    private void onClinK() {
        nameRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                new UserModule().openRegisterActivity(getActivity(),null);
            }
        });
        contactTheCustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactPopupWindow();
            }
        });
    }
    private void contactPopupWindow() {



    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}