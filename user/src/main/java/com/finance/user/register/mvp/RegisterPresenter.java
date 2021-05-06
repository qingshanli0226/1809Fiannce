package com.finance.user.register.mvp;

import com.finance.framework.BasePresenter;
import com.finance.net.bean.LoginBean;
import com.finance.net.bean.RegisterBean;
import com.finance.net.model.RetrofitCreator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<IRegisterView> {
    public RegisterPresenter(IRegisterView iRegisterView){
        attachView(iRegisterView);
    }

    public void getRegisterData(String name,String pwd){
        RetrofitCreator.getFinanceApiService()
                .getRegister(name,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (iView!=null){
                            iView.onRegisterData(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAutoLogin(String token){
        RetrofitCreator.getFinanceApiService()
                .getAutoLogin(token)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (iView != null) {
                            iView.onAutoLogin(loginBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
