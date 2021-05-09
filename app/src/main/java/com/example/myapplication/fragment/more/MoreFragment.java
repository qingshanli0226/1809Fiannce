package com.example.myapplication.fragment.more;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseFragment;
import com.example.framework.view.ToolBar;
import com.example.myapplication.R;
import com.example.demo.Demo;
import com.example.sp.SpUtils;

import static com.example.demo.Demo.AROUTE_PATH_GESTUREPASSWORD;


public class MoreFragment extends BaseFragment {


    private ToolBar toolbar;
    private TextView personRegister;
    private ImageView showImg;
    private boolean isShow = false;

    @Override
    protected void initData() {

        personRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Demo.AROUTE_PATH_REGISTER).withInt("",1).navigation();
            }
        });

        showImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow){
                    isShow = false;
                    showImg.setImageDrawable(getResources().getDrawable(R.drawable.toggle_off));
                    SpUtils.putGestureBoolean(getContext(),false);
                }else {
                    isShow = true;
                    showImg.setImageDrawable(getResources().getDrawable(R.drawable.toggle_on));
                    SpUtils.putGestureBoolean(getContext(),true);
                    ARouter.getInstance().build(AROUTE_PATH_GESTUREPASSWORD).navigation();
                }
            }
        });

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) mView.findViewById(R.id.toolbar);
        personRegister = (TextView) mView.findViewById(R.id.person_register);
        showImg = (ImageView) mView.findViewById(R.id.showImg);
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