package com.fiannce.myapplication.fragment.more;


import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseFragment;
import com.fiannce.framework.view.ToolBar;
import com.fiannce.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends BaseFragment {


    private ToolBar toobarFake;
    private LinearLayout userRegister;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initData() {
        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getInstance().build("/main/MainActivity").withInt("", 1).navigation();
                ARouter.getInstance().build("/user/RegisterActivity").withInt("",2).navigation();
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        toobarFake = (ToolBar) mView.findViewById(R.id.toobar_fake);
        userRegister = (LinearLayout) mView.findViewById(R.id.user_register);
    }

    @Override
    protected int getLayout() {
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
