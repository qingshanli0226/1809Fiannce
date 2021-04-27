package com.example.a1809fiannce.all;

import com.example.framwork.base.BasePresenter;
import com.example.network.model.AllBean;
import com.example.network.retrofit.RetrofitManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AllPresenter extends BasePresenter<AllCallBack>{

    public AllPresenter(AllCallBack allCallBack) {
        addThouView(allCallBack);
    }
    public void AllData(){
        RetrofitManager.getRetrofit()
                .AllData()
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                            iView.ShowLoading();
                            addView(disposable);
                        }

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView!=null){
                            iView.HideLoading();
                        }

                    }
                })
                .subscribe(new Observer<AllBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AllBean allBean) {
                        iView.AllData(allBean);
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
