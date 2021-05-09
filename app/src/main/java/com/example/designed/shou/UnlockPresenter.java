package com.example.designed.shou;

import com.fiannce.bawei.framework.BasePresenter;
import com.fiannce.bawei.net.RetrofitCreator;
import com.fiannce.bawei.net.model.SetGesturesBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UnlockPresenter extends BasePresenter<IunlockView> {

    public UnlockPresenter(IunlockView iunlockView){
        setiView(iunlockView);
    }

    public void getUnlock(String msg){
        RetrofitCreator.getfiannceApiService()
                .getSetgestures(msg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .subscribe(new Observer<SetGesturesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SetGesturesBean setGesturesBean) {
                            if (iView != null){
                                iView.getUnlockData(setGesturesBean);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (iView != null){
                            iView.showError(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
