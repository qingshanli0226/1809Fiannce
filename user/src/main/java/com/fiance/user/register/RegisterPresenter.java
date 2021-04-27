package com.fiance.user.register;

import android.util.Log;

import com.fiance.framework.BasePresenter;
import com.fiance.net.RetrofitCreator;
import com.fiance.net.mode.RegisterBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterView> {
    private static final String TAG = "RegisterPresenter";
    public RegisterPresenter(RegisterView registerView){
        attachView(registerView);
    }
    public void getRegisterData(String name,String password){
        RetrofitCreator.getFiannceApiService()
                .getRegisterData(name,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                    iView.onRegisterData(registerBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                     Log.i(TAG, "onError: "+e.getMessage());
                     iView.showError(e.getMessage());

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

}
