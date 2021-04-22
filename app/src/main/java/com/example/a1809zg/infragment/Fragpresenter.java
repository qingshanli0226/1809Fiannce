package com.example.a1809zg.infragment;

import com.example.frame.Basepresenter;
import com.example.net.Retrofitmanager;
import com.example.net.bean.ProductBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Fragpresenter extends Basepresenter<FragView> {
    public Fragpresenter(FragView fragView) {
        attachView(fragView);

    }
    public void ProDuctData() {
        Retrofitmanager.getApi()
                .getProductData()
                .delay(2, TimeUnit.SECONDS)
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
                .subscribe(new Observer<ProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ProductBean productBean) {
                          if (mView!=null){
                              mView.onProdactDada(productBean);
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
