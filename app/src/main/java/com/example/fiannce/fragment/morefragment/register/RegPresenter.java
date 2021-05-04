package com.example.fiannce.fragment.morefragment.register;

import com.example.fiannce.fragment.BeanBack;
import com.example.framework.BasePresenter;
import com.example.framework.manager.RetrofitManager;
import com.example.net.mode.RegBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegPresenter extends BasePresenter<BeanBack> {

    public RegPresenter(BeanBack beanBack) {
        attachView(beanBack);
    }

    public void RegData(String user,String pwd){
        RetrofitManager.getRetrofit()
                .RegData(user,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iView.showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegBean regBean) {
                        iView.RegData(regBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        iView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
