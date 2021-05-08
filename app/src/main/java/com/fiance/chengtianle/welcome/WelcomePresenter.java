package com.fiance.chengtianle.welcome;


import com.fiance.framework.BasePresenter;
import com.fiance.net.RetrofitCreator;
import com.fiance.net.mode.HomeBean;
import com.fiance.net.mode.VersionBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WelcomePresenter extends BasePresenter<IWelcomeView> {
    public WelcomePresenter(IWelcomeView iWelcomeView){
        attachView(iWelcomeView);
    }
    public void getHomeData(){
        RetrofitCreator.getFiannceApiService()
                .getHomeData()
                .delay(2, TimeUnit.SECONDS)
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
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                       if (iView!=null){
                           iView.onHomeData(homeBean);
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
    public void getVersionData() {
        RetrofitCreator.getFiannceApiService()
                .getServerVersion()
                .delay(2,TimeUnit.SECONDS)
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
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VersionBean versionBean) {
                        if (iView!=null) {
                            iView.onVersionData(versionBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView!=null){
                            iView.showError(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}