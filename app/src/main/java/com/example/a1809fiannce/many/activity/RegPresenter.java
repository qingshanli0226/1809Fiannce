package com.example.a1809fiannce.many.activity;

import com.example.framwork.base.BasePresenter;
import com.example.network.model.RegBean;
import com.example.network.retrofit.RetrofitManager;

import javax.security.auth.callback.Callback;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegPresenter extends BasePresenter<RegisterCallBack> {

    public RegPresenter(RegisterCallBack callback) {
        addThouView(callback);
    }

    public void RegData(String user,String pwd){
        RetrofitManager.getRetrofit()
                .RegData(user,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.ShowLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.HideLoading();
                    }
                })
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegBean regBean) {
                        iView.RegData(regBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        iView.Error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
