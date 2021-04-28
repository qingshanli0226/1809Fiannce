package com.example.user.login;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.model.LoginBean;
import com.example.net.RetrofitCretor;
import com.example.user.register.IPersonRegisterView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PersonLoginPresenter extends BasePresenter<IPersonLoginView> {
    public PersonLoginPresenter(IPersonLoginView iPersonLoginView){
        attachView(iPersonLoginView);
    }

    public void postLogin(String name,String pwd){
        RetrofitCretor.getFiannceApiService()
                .postLogin(name, pwd)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (IView!=null){
                            IView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                        if (IView!=null){
                            IView.onLogin(loginBean);
                            LogUtils.json(loginBean);
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}

