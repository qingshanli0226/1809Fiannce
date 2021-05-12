package com.example.myapplication.fragment.more;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.demo.Demo;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.myapplication.R;
import com.example.sp.SpUtils;

import static com.example.demo.Demo.AROUTE_PATH_CLEARGESTUREPASSWORD;
import static com.example.demo.Demo.AROUTE_PATH_GESTUREPASSWORD;


public class MoreFragment extends BaseFragment {


    private ToolBar toolbar;
    private TextView personRegister;
    private ImageView showImg;
    private boolean isShow = false;
    private TextView callPerson;
    private TextView resetPassword;

    @Override
    protected void initData() {

        callPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        personRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Demo.AROUTE_PATH_REGISTER).withInt("", 1).navigation();
            }
        });

        showImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean aBoolean = SpUtils.getGestureBoolean(getContext());
//                Log.i("zrf", "onClick: " + aBoolean);

                if (aBoolean == true && FiannceUserManager.getInstance().isLogin() == true) {
                    showImg.setImageDrawable(getResources().getDrawable(R.drawable.toggle_off));
                    SpUtils.putGestureBoolean(getContext(), false);
                } else if (aBoolean == false && FiannceUserManager.getInstance().isLogin() == true) {
                    showImg.setImageDrawable(getResources().getDrawable(R.drawable.toggle_on));
                    SpUtils.putGestureBoolean(getContext(), true);
                    ARouter.getInstance().build(AROUTE_PATH_GESTUREPASSWORD).navigation();
                } else if (aBoolean == false && FiannceUserManager.getInstance().isLogin() == false) {
                    Toast.makeText(getContext(), "请先登录,再进行设置手势密码", Toast.LENGTH_SHORT).show();
                    SpUtils.putGestureBoolean(getContext(), true);
                }
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(AROUTE_PATH_CLEARGESTUREPASSWORD).navigation();
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
        callPerson = (TextView) mView.findViewById(R.id.call_person);
        resetPassword = (TextView) mView.findViewById(R.id.reset_password);


        Boolean aBoolean = SpUtils.getGestureBoolean(getContext());
        if (aBoolean) {
            showImg.setImageDrawable(getResources().getDrawable(R.drawable.toggle_on));
        } else {
            showImg.setImageDrawable(getResources().getDrawable(R.drawable.toggle_off));
        }

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