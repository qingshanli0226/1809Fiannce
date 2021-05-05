package com.example.a1809fiannce.main.more;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.a1809fiannce.R;
import com.example.commom.FianceConstants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.view.ToolBar;


public class MoreFragment extends BaseFragment {

    private RelativeLayout fragMoreUserRigister;
    private ToolBar toolbar;
    private RelativeLayout fragMorePhoneCall;
    private ImageView fragMorePwdOff;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CALL_PHONE}, 100);
        }

        fragMoreUserRigister.setOnClickListener(view -> {
//            ARouter.getInstance().build("/user/LoginActivity").withString("","").navigation();
//            startActivity(new Intent(getActivity(), LoginActivity.class));
//            FiannceArouter.getInstance().getiUsetInterface().openReginActivity(getActivity(),null);
            FiannceArouter.getInstance().build(FianceConstants.REGISTER_PATH).navigation();
        });
        fragMorePhoneCall.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + R.string.contactCustomerPhone));
            startActivity(intent);
        });

        fragMorePwdOff.setOnClickListener(view -> {
            fragMorePwdOff.setImageResource(R.drawable.toggle_on);
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        fragMoreUserRigister = (RelativeLayout) findViewById(R.id.frag_more_user_rigister);

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        fragMorePhoneCall = (RelativeLayout) findViewById(R.id.frag_more_phone_call);
        fragMorePwdOff = (ImageView) findViewById(R.id.frag_more_pwd_off);
    }
}