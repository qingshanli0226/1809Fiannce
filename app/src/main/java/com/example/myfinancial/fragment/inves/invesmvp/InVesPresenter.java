package com.example.myfinancial.fragment.inves.invesmvp;

import android.util.Log;

import com.example.framework.BasePresenter;
import com.example.net.FiannceHttpMannager;
import com.example.net.bean.AllMoneyBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class InVesPresenter extends BasePresenter<IVesView> {

    public InVesPresenter(IVesView inVesView) {
        attView(inVesView);
    }
    public void getAllMoney(){
        FiannceHttpMannager.getApiModel().getAll()
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        if (mView!=null){
                            mView.showLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView!=null){
                            mView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<AllMoneyBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AllMoneyBean allMoneyBean) {
                        if (mView!=null){
                            mView.initAllMoney(allMoneyBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            Log.d("InVesPresenter", e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
