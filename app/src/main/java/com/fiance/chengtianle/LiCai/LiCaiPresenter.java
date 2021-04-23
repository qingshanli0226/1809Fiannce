package com.fiance.chengtianle.LiCai;

import android.util.Log;

import com.fiance.framework.BasePresenter;
import com.fiance.net.RetrofitCreator;
import com.fiance.net.mode.LcBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LiCaiPresenter extends BasePresenter<ILiCaiView> {
    public LiCaiPresenter(ILiCaiView iLiCaiView) {
        attachView(iLiCaiView);
    }

    public void getLiCaiData1(){

        RetrofitCreator.getFiannceApiService()
                .getLiCaiData()
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
                .subscribe(new Observer<LcBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LcBean lcBean) {
                    iView.onLiCaiData(lcBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("LiCaiPresenter", "113" + e.getMessage());
                        iView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
