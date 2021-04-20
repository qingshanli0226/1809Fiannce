package com.example.a1809fiannce.Welcome;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.HoemBean;
import com.example.net.model.VersionBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WelcomePresenter extends BasePresenter<WelcomeView> {

    public WelcomePresenter(WelcomeView welcomeView) {
        attachView(welcomeView);
    }

    public void getServerVersion(){
        RetrofitCreator.getFiannceApiService()
                .getServerVersion()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                })
                .doFinally(() -> {

                })
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull VersionBean versionBean) {
                        iView.onWelcomeData(versionBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        iView.Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getHomeData(){
        RetrofitCreator.getFiannceApiService()
                .getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                })
                .doFinally(() -> {

                })
                .subscribe(new Observer<HoemBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HoemBean hoemBean) {
                        iView.onHomeData(hoemBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        iView.Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
