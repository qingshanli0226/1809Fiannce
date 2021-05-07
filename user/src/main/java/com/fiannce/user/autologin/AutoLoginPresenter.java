package com.fiannce.user.autologin;

import com.fiannce.framework.BasePresenter;
import com.fiannce.net.RetrofitCreator;
import com.fiannce.net.mode.AutoLoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AutoLoginPresenter extends BasePresenter<IAutoLoginView> {

    public AutoLoginPresenter (IAutoLoginView autoLoginView){
        attachView(autoLoginView);
    }

    public void getAutoLogin(String token){
        RetrofitCreator.getFiannceApiService()
                .getAutologin(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<AutoLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AutoLoginBean autoLoginBean) {
                        if (iView != null){
                            iView.AutoLogin(autoLoginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView != null){
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
