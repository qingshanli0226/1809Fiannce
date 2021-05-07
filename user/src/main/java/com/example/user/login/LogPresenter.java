package com.example.user.login;

import android.util.Log;

import com.example.framework.BasePresenter;
import com.example.framework.manager.RetrofitManager;
import com.example.net.mode.LogBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LogPresenter extends BasePresenter<LogCallBack> {

    public LogPresenter(LogCallBack beanBack){
        attachView(beanBack);
    }

    public void LogData(String name,String pwd){
        RetrofitManager.getRetrofit()
                .LogData(name, pwd)
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
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("123", "onSubscribe: first");
                    }

                    @Override
                    public void onNext(@NonNull LogBean logBean) {
                        Log.i("123", "onNext: 111111111111111");
                        iView.LogData(logBean);
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
