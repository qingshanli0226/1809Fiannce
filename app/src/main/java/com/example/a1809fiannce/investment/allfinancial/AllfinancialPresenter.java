package com.example.a1809fiannce.investment.allfinancial;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.mode.AllfinancialBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AllfinancialPresenter extends BasePresenter<IAllfinancial> {

    public AllfinancialPresenter(IAllfinancial iAllfinancial) {
        attachView(iAllfinancial);
    }

    public void getAllfinancial(){
        RetrofitCreator.getFiannceApiService()
                .getAllfinancial()
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
                .subscribe(new Observer<AllfinancialBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AllfinancialBean allfinancialBean) {
                        if (iView!=null){
                            iView.onAllfinancial(allfinancialBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.Error(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
