package com.example.user.mvp;

import com.example.framework.BasePresenter;
import com.example.net.FiannceHttpMannager;
import com.example.net.bean.AutoBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.LogoutBean;
import com.example.net.bean.RegisterBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MorePresenter extends BasePresenter<MoreView> {
    public MorePresenter(MoreView view) {
        attView(view);
    }
    public void onRegister(String name, String pwd){
        FiannceHttpMannager.getApiModel().getRegister(name,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (mView!=null){
                            mView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView!=null){
                            mView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        if (mView!=null){
                            mView.initRegister(registerBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    public void onLogin(String name, String pwd){
        FiannceHttpMannager.getApiModel().getLogin(name,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (mView!=null){
                            mView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView!=null){
                            mView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (mView!=null){
                            mView.initLogin(loginBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getAuto(String token){
        FiannceHttpMannager.getApiModel().getAuto(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (mView!=null){
                            mView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView!=null){
                            mView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<AutoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AutoBean autoBean) {
                        if (mView!=null){
                            mView.initAuto(autoBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //退出登陆
    public void onUnLogin(String token){
        FiannceHttpMannager.getApiModel().getLogout(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (mView!=null){
                        mView.showLoading();
                    }
                })
                .doFinally(() -> {
                    if (mView!=null){
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<LogoutBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LogoutBean logoutBean) {
                        if (mView!=null){
                            mView.initLogout(logoutBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
