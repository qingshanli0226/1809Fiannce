package com.example.a1809fiannce.welcome;

import android.util.Log;

import com.example.framwork.base.BasePresenter;
import com.example.network.model.HomeBean;
import com.example.network.model.UpdateBean;
import com.example.network.retrofit.RetrofitManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WelcomePresenter extends BasePresenter<WelcomeCallBack> {
    public WelcomePresenter(WelcomeCallBack welcomeCallBack) {
        addThouView(welcomeCallBack);
    }
    public void HomeData(){
        RetrofitManager.getRetrofit()
                .HomeData()
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                            addView(disposable);
                            iView.ShowLoading();
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
                .subscribe(new Observer<HomeBean>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onNext(@NonNull HomeBean homeBean) {
                                   if (iView!=null){
                                       iView.HomeData(homeBean);
                                       Log.i("zx", "HomeData: "+homeBean.toString());
                                   }

                               }

                               @Override
                               public void onError(@NonNull Throwable e) {
                                   if (iView!=null){
                                       iView.Error(e.getMessage());
                                   }

                               }

                               @Override
                               public void onComplete() {

                               }
                           }

                );
    }
    public void UpdateData(){
        RetrofitManager.getRetrofit()
                .UpdateData()
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
                .subscribe(new Observer<UpdateBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpdateBean updateBean) {
                        if (iView!=null){
                            iView.UpdateData(updateBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.Error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
