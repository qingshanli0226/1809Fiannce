package com.example.user.self;

import com.example.framework.BasePresenter;
import com.example.framework.manager.RetrofitManager;
import com.example.net.mode.LogBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SelfPresenter extends BasePresenter<SelfCallBack> {

    public SelfPresenter(SelfCallBack callBack){
        attachView(callBack);
    }

    public void RequestSelf(String token){
        RetrofitManager.getRetrofit()
                .AutoData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LogBean logBean) {
                        if (logBean != null){
                            iView.LogData(logBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
