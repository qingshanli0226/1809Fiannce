package com.example.user.exitlogin;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.GesturePassword;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ExitPresenter extends BasePresenter<IExitView> {
    public ExitPresenter(IExitView iExitView) {
        attchView(iExitView);
    }
    public void getExit(){
        RetrofitManager.getHttpApiService().exit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .subscribe(new Observer<GesturePassword>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GesturePassword gesturePassword) {
                        if (mView != null) {
                            mView.onExit(gesturePassword);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
