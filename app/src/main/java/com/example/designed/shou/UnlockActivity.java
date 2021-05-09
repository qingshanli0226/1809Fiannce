package com.example.designed.shou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.designed.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.model.SetGesturesBean;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

public class UnlockActivity extends BaseActivity<UnlockPresenter>  implements IunlockView{


    private GestureLockView glv;
    UnlockPresenter unlockPresenter;


    @Override
    protected void initData() {
        glv.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {
                    unlockPresenter.getUnlock(result);
            }
        });
    }

    @Override
    protected void initPresenter() {
        unlockPresenter = new UnlockPresenter(this);
    }

    @Override
    protected int getLaoutId() {
        return R.layout.activity_unlock;
    }

    @Override
    protected void initView() {
        glv = (GestureLockView) findViewById(R.id.glv);

    }

    @Override
    public void getUnlockData(SetGesturesBean setGesturesBean) {
        if (setGesturesBean.getCode().equals("200")){
            Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "设置失败", Toast.LENGTH_SHORT).show();
            glv.showErrorStatus(600);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}
