package com.example.a1809fiannce.invest.allfinancial;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.AllProductBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AllPresenter extends BasePresenter<IAllView> {

    public AllPresenter(IAllView iAllView) {
        attachView(iAllView);
    }

    public void getAllProductData() {
        RetrofitCreator.getFiannceApiService()
                .getAllProduct()
                .subscribeOn(Schedulers.io())
                .delay(2000, TimeUnit.MILLISECONDS)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        if (iView != null)
                            iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView != null)
                            iView.hileLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AllProductBean allProductBean) {
                        if (iView != null)
                            iView.onAllProductData(allProductBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null)
                            iView.Error(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
