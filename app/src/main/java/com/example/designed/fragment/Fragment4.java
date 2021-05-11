package com.example.designed.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.designed.MainActivity;
import com.example.designed.R;
import com.example.designed.eliminate.EliminateActivity;
import com.example.designed.shou.UnlockActivity;
import com.example.user.register.RegisterActivity;
import com.fiannce.bawei.framework.BaseFragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends BaseFragment {
    private LinearLayout register;
    private LinearLayout password;
    private ImageView off;
    private LinearLayout unpassword;
    private LinearLayout con;
    private LinearLayout user;
    private LinearLayout share;
    private LinearLayout about;
    private boolean isChanged = false;

    public Fragment4() {
        // Required empty public constructor
    }


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        register = (LinearLayout) findViewById(R.id.register);
        password = (LinearLayout) findViewById(R.id.password);
        off = (ImageView) findViewById(R.id.off);
        unpassword = (LinearLayout) findViewById(R.id.unpassword);
        con = (LinearLayout) findViewById(R.id.con);
        user = (LinearLayout) findViewById(R.id.user);
        share = (LinearLayout) findViewById(R.id.share);
        about = (LinearLayout) findViewById(R.id.about);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UMImage image = new UMImage(getActivity(), "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2858426577,4189650377&fm=26&gp=0.jpg");//网络图片

                new ShareAction(getActivity())
                        .withMedia(image)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                Toast.makeText(getActivity(), "throwable:" + throwable, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        })//回调监听器
                        .share();

            }
        });


        unpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EliminateActivity.class));
            }
        });



        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+"010-56253825"));
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), RegisterActivity.class));

            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    off.setImageResource(R.drawable.toggle_on);
                    Glide.with(getActivity()).load(R.drawable.toggle_on).into(off);
                     Toast.makeText(getActivity(), "开启手势密码", Toast.LENGTH_SHORT).show();

                     AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                     builder.setTitle("设置手势密码");
                     builder.setMessage("是否现在设置手势密码");

                     builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {

                             Intent intent = new Intent(getActivity(), UnlockActivity.class);
                             startActivity(intent);
                         }
                     });

                     builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             Glide.with(getActivity()).load(R.drawable.toggle_off).into(off);
                             Toast.makeText(getActivity(), "关闭手势密码", Toast.LENGTH_SHORT).show();
                         }
                     });

                     builder.show();




                    }






        });
    }

    @Override
    protected int getLoutId() {
        return R.layout.fragment_blank_fragment4;
    }

}
