package com.example.a1809fiannce.main.more;

import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.a1809fiannce.R;
import com.example.framework.BaseFragment;


public class MoreFragment extends BaseFragment{

    private RelativeLayout fragMoreUserRigister;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData() {

        fragMoreUserRigister.setOnClickListener(view -> {
            ARouter.getInstance().build("/user/LoginActivity").withString("","").navigation();
//            startActivity(new Intent(getActivity(), LoginActivity.class));
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