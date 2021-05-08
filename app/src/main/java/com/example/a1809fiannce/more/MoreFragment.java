package com.example.a1809fiannce.more;

import android.content.Intent;
import android.net.Uri;

import android.view.View;

import android.widget.RelativeLayout;

import com.example.a1809fiannce.R;

import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.view.ToolBar;

public class MoreFragment extends BaseFragment {
    private ToolBar toolbar;
    private RelativeLayout register;
    private RelativeLayout moneyManagement;
    private RelativeLayout morePhone;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        register = (RelativeLayout) findViewById(R.id.more_register);
        moneyManagement = (RelativeLayout) findViewById(R.id.money_management);
        morePhone = (RelativeLayout) findViewById(R.id.more_phone);
    }

    @Override
    protected void initData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiannceArouter.getInstance().getIUserInterface().openRegisterActivity(getContext(),null);
            }
        });

        moneyManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                File f=new File("/sdcard/Download/1809Fiannce.apk");
//                Uri uri;
//
//                try{//这里有文件流的读写，需要处理一下异常
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//                        //如果SDK版本>=24，即：Build.VERSION.SDK_INT >= 24
//                        String packageName = getActivity().getApplicationContext().getPackageName();
//                        String authority =  new StringBuilder(packageName).append(".provider").toString();
//                        uri = FileProvider.getUriForFile(getContext(), authority, f);
//                        intent.setDataAndType(uri, "application/vnd.android.package-archive");
//                    } else{
//                        uri = Uri.fromFile(f);
//                        intent.setDataAndType(uri, "application/vnd.android.package-archive");
//                    }
//                    startActivity(intent);
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });

        morePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:010-56253825"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPresenter() {

    }
}