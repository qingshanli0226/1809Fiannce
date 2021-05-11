package com.example.designed.eliminate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.example.designed.R;
import com.fiannce.bawei.framework.BaseActivity;
import com.fiannce.bawei.net.model.EliminateBean;
import com.wangnan.library.GestureLockThumbnailView;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

public class EliminateActivity extends BaseActivity<EliminatePresenter> implements IEliminateView{

    private GestureLockView glv;
    private EliminatePresenter eliminatePresenter;
    private GestureLockThumbnailView glt;





    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {



        glv.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {

                glv.clearView();

                glt.setThumbnailView(result, Color.BLUE);
                eliminatePresenter.getEliminate(result);

            }
        });
    }

    @Override
    protected int getLaoutId() {
        return R.layout.activity_eliminate;
    }

    @Override
    protected void initView() {
        glv = (GestureLockView) findViewById(R.id.glv);
        glt = (GestureLockThumbnailView) findViewById(R.id.glt);

    }

    @Override
    public void getEliminateData(EliminateBean eliminateBean) {

        LogUtils.json(eliminateBean);
        if (eliminateBean.getCode().equals("200")){
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
