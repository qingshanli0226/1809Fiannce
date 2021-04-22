package com.fiannce.zhaoyuzan.invest;

import com.fiannce.framework.BasePresenter;
import com.fiannce.net.RetrofitCreator;
import com.fiannce.net.mode.InvestBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class InvestPresenter extends BasePresenter<IInvestView> {

    public InvestPresenter(IInvestView iInvestView) {
        attachView(iInvestView);
    }

    public void getProductData(){
        RetrofitCreator.getFiannceApiService().getProductData()
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
                .subscribe(new Observer<InvestBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(InvestBean investBean) {
                if (iView != null){
                    iView.onProductData(investBean);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                if (iView != null){
                    iView.showToast(throwable.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
