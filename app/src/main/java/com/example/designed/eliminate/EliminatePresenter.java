package com.example.designed.eliminate;

import com.fiannce.bawei.framework.BasePresenter;
import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.model.EliminateBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EliminatePresenter extends BasePresenter<IEliminateView>  {

    public EliminatePresenter (IEliminateView iEliminateView){
        setiView(iEliminateView);
    }

    public void getEliminate(String msg){

        RetrofitCreator.getfiannceApiService()
                .getEliminate(msg)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                       add(disposable);
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                })
                .subscribe(new Observer<EliminateBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EliminateBean eliminateBean) {
                            if (iView != null){
                                iView.getEliminateData(eliminateBean);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
