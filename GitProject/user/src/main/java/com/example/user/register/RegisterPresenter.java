package com.example.user.register;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.ProductBean;
import com.example.net.bean.RegisterBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<IRegisterView> {
    public RegisterPresenter(IRegisterView iRegisterView) {
        attchView(iRegisterView);
    }
    public void getRegister(String name,String password){
        RetrofitManager.getHttpApiService()
                .getRegister(name,password)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        mView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView != null) {
                            mView.onRegister(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage().toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
