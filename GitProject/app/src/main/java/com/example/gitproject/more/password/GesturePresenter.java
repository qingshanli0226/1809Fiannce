package com.example.gitproject.more.password;

import android.util.Log;
import android.widget.Toast;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.GesturePassword;
import com.google.gson.Gson;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GesturePresenter  extends BasePresenter<IGestureView> {
    public GesturePresenter(IGestureView iGestureView) {
        attchView(iGestureView);
    }
    public void setGeseture(Map<String,String> map){
        String s = new Gson().toJson(map);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService().setGesturePassword(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GesturePassword>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GesturePassword gesturePassword) {
                        if (mView != null) {
                            mView.onSetGesture(gesturePassword);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void loginGeseture(Map<String,String> map){
        String s = new Gson().toJson(map);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);
        RetrofitManager.getHttpApiService().loginGesturePassword(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GesturePassword>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GesturePassword gesturePassword) {
                        if (mView != null) {
                            mView.onLoginGesture(gesturePassword);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void clearGeseture(){
        RetrofitManager.getHttpApiService().clearPassword()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GesturePassword>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GesturePassword gesturePassword) {
                        if (mView != null) {
                            mView.onClearGesture(gesturePassword);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView != null) {
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
