package com.fiannce.bawei.gesturelock;

import android.util.Log;

import com.fiannce.framework.BasePresenter;
import com.fiannce.net.RetrofitCreator;
import com.fiannce.net.mode.GestureBean;
import com.fiannce.net.mode.HomeBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GesturePresenter extends BasePresenter<IGestureView> {

    public GesturePresenter(IGestureView iGestureView) {
        attachView(iGestureView);
    }


    public void getUnlockData(String str){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gPassword",str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreator.getFiannceApiService()
                .setPassword(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .subscribe(new Observer<GestureBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GestureBean gestureBean) {
                        if (iView!=null){
                            iView.onGestureData(gestureBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showToast(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void verifyUnlockData(String str){


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gPassword",str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreator.getFiannceApiService()
                .loginPassword(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .subscribe(new Observer<GestureBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(GestureBean gestureBean) {
                        if (iView!=null){
                            iView.verifyUnlockData(gestureBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showToast(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void clearUnlockData(String str){
        Log.i("zyz", "initData: "+str);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gPassword",str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreator.getFiannceApiService()
                .clearPassword(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .subscribe(new Observer<GestureBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GestureBean gestureBean) {
                        if (iView!=null){
                            iView.clearUnlockData(gestureBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showToast(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
