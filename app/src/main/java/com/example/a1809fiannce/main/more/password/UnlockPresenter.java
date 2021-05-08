package com.example.a1809fiannce.main.more.password;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.UnlockBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UnlockPresenter extends BasePresenter<IUnlockView> {
    public UnlockPresenter(IUnlockView iUnlockView) {
        attachView(iUnlockView);
    }

    public void getUnlockData(String str){
        RetrofitCreator.getFiannceApiService()
                .getUnlockData(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    add(disposable);
                })
                .subscribe(new Observer<UnlockBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UnlockBean unlockBean) {
                        if (iView!=null){
                            iView.getUnlockData(unlockBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.Error(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
