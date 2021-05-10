package com.fiannce.bawei.user;

import com.blankj.utilcode.util.LogUtils;
import com.fiannce.bawei.framework.BasePresenter;
import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.user.login.bean.LoginBean;
import com.fiannce.bawei.net.user.register.bean.RegisterBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends BasePresenter<UserView> {
        public UserPresenter(UserView view){
            attachView(view);
        }
        public void UserLogin(String name,String password){
                RetrofitCreator.getFiannceApiService().getLogin(name,password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LoginBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(LoginBean loginBean) {
                                        if (iView !=null){
                                                iView.onLoginData(loginBean);
                                        }
                                }

                                @Override
                                public void onError(Throwable e) {
                                         LogUtils.e(""+e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                        });

        }
        public void UserRegister(String name,String password){
                RetrofitCreator.getFiannceApiService()
                        .getRegister(name,password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RegisterBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(RegisterBean registerBean) {
                                        if (iView !=null ){
                                                iView.onRegisterData(registerBean);
                                        }
                                }

                                @Override
                                public void onError(Throwable e) {
                                        LogUtils.e(""+e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                        });
        }

    public void getAutoLogin(String token) {
        RetrofitCreator.getFiannceApiService()
                .getAutoLogin(token)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
//                        iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
//                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (iView != null) {
                            iView.onAutoLogin(loginBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null) {
//                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
