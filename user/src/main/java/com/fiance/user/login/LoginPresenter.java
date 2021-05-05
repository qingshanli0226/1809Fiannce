package com.fiance.user.login;

import com.fiance.framework.BasePresenter;
import com.fiance.net.RetrofitCreator;
import com.fiance.net.mode.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView loginView){
        attachView(loginView);
    }
    public void getLoginData(String name,String password){
        RetrofitCreator.getFiannceApiService()
                .getLoginData(name,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        iView.onLoginData(loginBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                         iView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
