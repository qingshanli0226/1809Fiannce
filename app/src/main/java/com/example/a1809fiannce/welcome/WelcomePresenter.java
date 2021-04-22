package com.example.a1809fiannce.welcome;

import com.fiannce.bawei.framework.BasePresenter;
import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.mode.HomeBean;
import com.fiannce.bawei.net.mode.VersionBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WelcomePresenter extends BasePresenter<IWelcomeView> {

    public WelcomePresenter(IWelcomeView iWelcomeView) {
        attachView(iWelcomeView);
    }

    public void getHomeData() {
        RetrofitCreator.getFiannceApiService()
                .getHomeData()
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
                .doFinally(new Action() {//当网络请求结束时回调该方法
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (iView!=null) {
                            iView.onHomeData(homeBean);
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
