package com.example.user.autologin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.view.ToolBar;
import com.example.model.AutoLoginBean;
import com.example.sp.SpUtils;

public class AutoLoginService extends Service {
    public AutoLoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!SpUtils.getString(AutoLoginService.this).equals("")){
            new PersonAutoLoginPresenter(new IPersonLoginAutoView() {
                @Override
                public void autoLogin(AutoLoginBean autoLoginBean) {
                    LogUtils.json(autoLoginBean);
//                AutoLoginBean.ResultBean result = autoLoginBean.getResult();
//                String token = result.getToken();
                    if (autoLoginBean.getCode().equals("200")){
                        Toast.makeText(AutoLoginService.this, "自动登录成功", Toast.LENGTH_SHORT).show();
                        SpUtils.putString(AutoLoginService.this,autoLoginBean.getResult().getToken());
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
            }).postAutoLogin(SpUtils.getString(this));
        }


        return super.onStartCommand(intent, flags, startId);
    }
}