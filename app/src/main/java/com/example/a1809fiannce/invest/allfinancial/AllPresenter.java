package com.example.a1809fiannce.invest.allfinancial;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.AllProductBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AllPresenter extends BasePresenter<IAllView> {

    public AllPresenter(IAllView iAllView) {
        attachView(iAllView);
    }

    public void getAllProductData() {
        RetrofitCreator.getFiannceApiService()
                .getAllProduct()
                .subscribeOn(Schedulers.io())
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
