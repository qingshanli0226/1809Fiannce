package com.example.user;

import com.example.frame.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends BasePresenter<IUserView> {
    public UserPresenter(IUserView iUserView) {
        attachView(iUserView);
    }
    public void getRegister(String name,String password){
        RetrofitManager.getApi()
                .getReisterData(name, password)
                .delay(2, TimeUnit.MINUTES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        mView.showLoaing();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                             if (mView!=null){
                                 mView.onRegister(registerBean);
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
    public void getLogin(String name, String password){
        RetrofitManager.getApi()
                .getLoginData(name, password)
                .delay(2,TimeUnit.MINUTES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        mView.showLoaing();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                          if (mView!=null){
                              mView.onLogin(loginBean);
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
    public void getAutoLogin(String token){
        RetrofitManager.getApi()
                .getAutoData(token)
                .delay(2,TimeUnit.MINUTES)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        mView.showLoaing();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                           if (mView!=null){
                               mView.onAutoLogin(loginBean);
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
