package com.example.a1809fiannce.main.more;

import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.a1809fiannce.R;
import com.example.commom.FianceConstants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceArouter;
import com.example.user.register.RegisterActivity;


public class MoreFragment extends BaseFragment{

    private RelativeLayout fragMoreUserRigister;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData() {

        fragMoreUserRigister.setOnClickListener(view -> {
//            ARouter.getInstance().build("/user/LoginActivity").withString("","").navigation();
//            startActivity(new Intent(getActivity(), LoginActivity.class));
//            FiannceArouter.getInstance().getiUsetInterface().openReginActivity(getActivity(),null);

            FiannceArouter.getInstance().build(FianceConstants.REGISTER_PATH).navigation();

        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        fragMoreUserRigister = (RelativeLayout) findViewById(R.id.frag_more_user_rigister);

    }
}