package com.fiannce.user.login;

import com.fiannce.framework.BasePresenter;
import com.fiannce.framework.IBaseView;
import com.fiannce.net.RetrofitCreator;
import com.fiannce.net.mode.LoginBean;
import com.google.android.exoplayer2.C;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView loginView) {
        attachView(loginView);
    }

    public void getLogin(String name,String password){
        RetrofitCreator.getFiannceApiService()
                .getLogin(name,password)
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
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (iView != null){
                            iView.getLogin(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView != null){
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
