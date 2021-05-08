package com.fiannce.bawei.user;

import com.blankj.utilcode.util.LogUtils;
import com.fiannce.bawei.framework.BasePresenter;
import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.user.login.bean.LoginBean;
import com.fiannce.bawei.net.user.register.bean.RegisterBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPresengter extends BasePresenter<UserView> {
        public UserPresengter(UserView view){
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

}
