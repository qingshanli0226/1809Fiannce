package com.example.myfinancial.welcome;

import android.util.Log;

import com.example.framework.BasePresenter;
import com.example.net.FiannceHttpMannager;
import com.example.net.bean.HomeBean;
import com.example.net.bean.VersionBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WelcomePresenter extends BasePresenter<WelComeView> {
    public WelcomePresenter(WelComeView welComeView) {
        attView(welComeView);
    }
    public void getVersion(){
        FiannceHttpMannager.getApiModel().getVersionData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {//开始网络连接
                        add(disposable);//添加订阅
                        mView.showLoading();//显示加载ui
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {//结束网络连接
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull VersionBean versionBean) {
                        if (mView!=null){
                            mView.initVersion(versionBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            mView.showError(e.getMessage());
                            Log.d("WelcomePresenter", e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getHome(){
        FiannceHttpMannager.getApiModel().getHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {//开始网络连接
                        add(disposable);//添加订阅
                        mView.showLoading();//显示加载ui
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {//结束网络连接
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                        if (mView!=null){
                            mView.initWelcome(homeBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            mView.showError("获取数据发生错误");
                            Log.d("WelcomePresenter", e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
