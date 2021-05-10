package com.example.a1809fiannce.main.more;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.example.a1809fiannce.R;
import com.example.commom.FianceConstants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.manager.FiannceUserManager;
import com.example.framework.view.ToolBar;

import java.io.File;


public class MoreFragment extends BaseFragment {

    private RelativeLayout fragMoreUserRigister;
    private ToolBar toolbar;
    private RelativeLayout fragMorePhoneCall;
    private ImageView fragMorePwdOff;
    private RelativeLayout fragMoreVerifyPwd;
    private RelativeLayout fragMoreOpenapk;


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
        fragMorePhoneCall.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + R.string.contactCustomerPhone));
            startActivity(intent);
        });

        fragMorePwdOff.setOnClickListener(view -> {
            fragMorePwdOff.setImageResource(R.drawable.toggle_on);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.opengesturalCode);
            builder.setNegativeButton(R.string.no, (dialogInterface, i) -> {
                fragMorePwdOff.setImageResource(R.drawable.toggle_off);
            });
            builder.setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                if (FiannceUserManager.getInstance().getLoginBean() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("judge", 1);
                    FiannceArouter.getInstance().build(FianceConstants.UNLOCK_PATH).navigation(bundle);
                } else {
                    fragMorePwdOff.setImageResource(R.drawable.toggle_off);
                    Toast.makeText(getActivity(), R.string.please_login_first, Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        });
        fragMoreVerifyPwd.setOnClickListener(view -> {
            if (CacheManager.getInstance().gesture) {
                Bundle bundle = new Bundle();
                bundle.putInt("judge", 3);
                FiannceArouter.getInstance().build(FianceConstants.UNLOCK_PATH).navigation(bundle);
                fragMorePwdOff.setImageResource(R.drawable.toggle_off);
            } else {
                Toast.makeText(getActivity(), R.string.pleaseCheckThatTheGesturePasswordIsEnabled, Toast.LENGTH_SHORT).show();
            }
        });
        fragMoreOpenapk.setOnClickListener(view -> {
            openAPK(FianceConstants.SD_DOWNLOAD);
        });
    }


    private void openAPK(String fileSavePath) {
        Toast.makeText(getActivity(), "aaa", Toast.LENGTH_SHORT).show();
        File file = new File(Uri.parse(fileSavePath).getPath());
        String filePath = file.getAbsolutePath();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
            // 生成文件的uri，，
            // 注意 下面参数com.ausee.fileprovider 为apk的包名加上.fileprovider，
            data = FileProvider.getUriForFile(getActivity(), "com.example.a1809fiannce.fileProvider", new File(filePath));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
        } else {
            data = Uri.fromFile(file);
        }
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        fragMoreUserRigister = (RelativeLayout) findViewById(R.id.frag_more_user_rigister);
        fragMoreVerifyPwd = (RelativeLayout) findViewById(R.id.frag_more_verify_pwd);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        fragMorePhoneCall = (RelativeLayout) findViewById(R.id.frag_more_phone_call);
        fragMorePwdOff = (ImageView) findViewById(R.id.frag_more_pwd_off);
        fragMoreOpenapk = (RelativeLayout) findViewById(R.id.frag_more_openapk);
    }
}