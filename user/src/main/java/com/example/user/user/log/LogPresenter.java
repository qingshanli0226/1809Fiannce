package com.example.user.user.log;

import com.example.framwork.base.BasePresenter;
import com.example.network.model.LogBean;
import com.example.network.retrofit.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LogPresenter extends BasePresenter<LogCallBack> {
    public LogPresenter(LogCallBack callBack) {
        addThouView(callBack);
    }



    public void LogData(String name,String pwd){
        RetrofitManager.getRetrofit()
                .LogData(name,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addView(disposable);
                        iView.ShowLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.HideLoading();
                    }
                })
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LogBean logBean) {
                        iView.LogData(logBean);
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
