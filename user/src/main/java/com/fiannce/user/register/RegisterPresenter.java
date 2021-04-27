package com.fiannce.user.register;

import com.fiannce.framework.BasePresenter;
import com.fiannce.net.FiannceApiService;
import com.fiannce.net.RetrofitCreator;
import com.fiannce.net.mode.AllMoneyBean;
import com.fiannce.net.mode.RegisterBean;
import com.google.android.exoplayer2.C;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<IRegisterView> {

    public  RegisterPresenter(IRegisterView iRegisterView){
        attachView(iRegisterView);
    }

    public void getRegister(String name,String password){
        RetrofitCreator.getFiannceApiService()
                .getRegister(name,password)
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
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (iView != null){
                            iView.onRegister(registerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
