package com.Fiannce.myapplication.fragment.investment.money.allmoeny;

import com.fiannce.framework.BasePresenter;
import com.fiannce.net.RetrofitCreator;
import com.fiannce.net.mode.AllMoneyBean;

import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AllmoneyPresenter extends BasePresenter<IAllmoneyView> {

    public AllmoneyPresenter(IAllmoneyView iAllmoneyView ){
        attachView(iAllmoneyView);
    }

    public void getAllmoney(){
        RetrofitCreator.getFiannceApiService()
                .getAllmoney()
                .delay(2, TimeUnit.SECONDS)
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
                .subscribe(new Observer<AllMoneyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AllMoneyBean allMoneyBean) {
                        if (iView != null){
                            iView.onAllmoney(allMoneyBean);
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
