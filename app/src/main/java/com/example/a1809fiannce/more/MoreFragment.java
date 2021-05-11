package com.example.a1809fiannce.more;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.example.a1809fiannce.MainActivity;
import com.example.a1809fiannce.R;
import com.example.a1809fiannce.gesturepassword.GesturePasswordActivity;
import com.example.commom.FiannceConstants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.FiannceArouter;
import com.example.framework.view.ToolBar;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;

import java.io.File;

public class MoreFragment extends BaseFragment {
    private ToolBar toolbar;
    private RelativeLayout register;
    private RelativeLayout moneyManagement;
    private RelativeLayout morePhone;
    private RelativeLayout share;
    private RelativeLayout moreRegister;
    private ImageView toggleOff;
    private boolean off=false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView() {
        share = (RelativeLayout) findViewById(R.id.share);
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        register = (RelativeLayout) findViewById(R.id.more_register);
        moneyManagement = (RelativeLayout) findViewById(R.id.money_management);
        morePhone = (RelativeLayout) findViewById(R.id.more_phone);
        moreRegister = (RelativeLayout) findViewById(R.id.more_register);
        toggleOff = (ImageView) findViewById(R.id.toggle_off);
    }

    @Override
    protected void initData() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiannceArouter.getInstance().getIUserInterface().openRegisterActivity(getContext(), null);
            }
        });

        moneyManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                File file = new File(Uri.parse(FiannceConstants.DOWNLOAD_PATH).getPath());
//                String filePath = file.getAbsolutePath();
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Uri data = null;
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
//                    // 生成文件的uri，，
//                    // 注意 下面参数com.ausee.fileprovider 为apk的包名加上.fileprovider，
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    data = FileProvider.getUriForFile(getContext(), "com.example.a1809fiannce.fileProvider", new File(filePath));
//                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
//                } else {
//                    data = Uri.fromFile(file);
//                }
//
//                intent.setDataAndType(data, "applicationnd.android.package-archive");
//                startActivity(intent);
            }
        });

        toggleOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (off){
                    toggleOff.setImageResource(R.drawable.toggle_off);
                    off=false;
                }else {
                    toggleOff.setImageResource(R.drawable.toggle_on);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle(getResources().getString(R.string.setGesturePassword));
                    builder.setMessage(getResources().getString(R.string.nowGesturePassword));

                    builder.setNeutralButton(getResources().getString(R.string.NO), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            toggleOff.setImageResource(R.drawable.toggle_off);

                            off=true;
                        }
                    });

                    builder.setPositiveButton(getResources().getString(R.string.YES), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getContext(), GesturePasswordActivity.class);
                            startActivity(intent);
                        }
                    });

                    builder.show();

                    off=true;
                }
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

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMImage image = new UMImage(getContext(), "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2858426577,4189650377&fm=26&gp=0.jpg");//网络图片

                new ShareAction(getActivity())
                        .withText("hello")
                        .withMedia(image)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                Toast.makeText(getContext(), ""+throwable.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }).open();
            }
        });
    }

    @Override
    protected void initPresenter() {

    }
}