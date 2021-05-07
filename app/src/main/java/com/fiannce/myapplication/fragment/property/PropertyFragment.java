package com.fiannce.myapplication.fragment.property;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fiannce.framework.BaseFragment;
import com.fiannce.framework.view.ToolBar;
import com.fiannce.myapplication.MainActivity;
import com.fiannce.myapplication.R;
import com.fiannce.net.mode.UserBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class PropertyFragment extends BaseFragment {


    private ToolBar toobarFake;
    private ImageView mindImg;
    private TextView tv;

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        toobarFake = (ToolBar) mView.findViewById(R.id.toobar_fake);
        mindImg = (ImageView) mView.findViewById(R.id.mind_img);
        tv = (TextView) mView.findViewById(R.id.tv);
        toobarFake.setToolbarListener(this);
    }

    @Subscribe
    public void EventLogin(UserBean userBean) {
        String name = userBean.getName();
        String possword = userBean.getPossword();
        tv.setText(name+"");
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_property;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {
        ARouter.getInstance().build("/user/unLoginActivity").withInt("",0).navigation();
    }

    @Override
    public void onRightTvClick() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
