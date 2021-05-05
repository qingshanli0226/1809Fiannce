package com.example.a1809fiannce.main.property;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1809fiannce.R;
import com.example.commom.FianceConstants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;
import com.example.net.model.LoginBean;


public class PropertyFragment extends BaseFragment implements FiannceUserManager.IUserLoginChanged {

    private ToolBar toolbar;
    private ImageView fragProperHand;
    private TextView fragProperName;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_property;
    }

    @Override
    protected void initData() {
        FiannceUserManager.getInstance().register(this);
        LoginBean loginBean = FiannceUserManager.getInstance().getLoginBean();
        if (loginBean!=null){
            fragProperName.setText(loginBean.getResult().getName());
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        fragProperHand = (ImageView) findViewById(R.id.frag_proper_hand);
        fragProperName = (TextView) findViewById(R.id.frag_proper_name);
    }

    @Override
    public void onRightImgClick() {
        super.onRightImgClick();
        FiannceArouter.getInstance().build(FianceConstants.USER_PATH).navigation();
    }

    @Override
    public void onLoginChange(LoginBean loginBean) {
        if (loginBean!=null){
            fragProperName.setText(loginBean.getResult().getName()+"");
        }else {
            fragProperName.setText("请先登录");
        }

    }


}