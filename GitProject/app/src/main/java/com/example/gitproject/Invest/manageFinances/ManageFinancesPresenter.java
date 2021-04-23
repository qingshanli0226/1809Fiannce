package com.example.gitproject.Invest.manageFinances;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.ProductBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ManageFinancesPresenter extends BasePresenter<IManageFinancesView> {

    public ManageFinancesPresenter(IManageFinancesView manageFinancesView) {
        attchView(manageFinancesView);
    }

    public void getIvnest(){
        RetrofitManager.getHttpApiService()
                .getProduct()
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading();
                        add(disposable);
                    }
                })
                .doFinally(() -> {
                    mView.hideLoading();
                })
                .subscribe(new Observer<ProductBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ProductBean productBean) {
                        if (mView != null) {
                            mView.onProduct(productBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });



//        RetrofitManager.getHttpApiService()
//                .getProduct()
//                .subscribeOn(Schedulers.io())
//                .delay(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        add(disposable);
//                        mView.showLoading();
//                    }
//                })
//                .doFinally(() -> {
//                    mView.hideLoading();
//                })
//                .subscribe(new Observer<ProductBean>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull ProductBean productBean) {
//                        if (mView != null) {
//                            mView.onProduct(productBean);
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        if (mView != null) {
//                            mView.showError(e.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
