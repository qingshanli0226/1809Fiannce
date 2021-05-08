package com.example.a1809fiannce.Gesture;

import com.example.framwork.base.BasePresenter;
import com.example.network.model.GesturePwd;
import com.example.network.retrofit.RetrofitManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GesturePresenter extends BasePresenter<CallGesture> {

    public GesturePresenter(CallGesture callGesture) {
        addThouView(callGesture);
    }

    public void GestureData(String aPwd){
        RetrofitManager.getRetrofit()
                .setGesturePwd(aPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GesturePwd>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GesturePwd gesturePwd) {
                        if (iView!=null){
                            iView.OnGestureData(gesturePwd);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            if (iView!=null){
                                iView.Error(e.getMessage());
                            }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
