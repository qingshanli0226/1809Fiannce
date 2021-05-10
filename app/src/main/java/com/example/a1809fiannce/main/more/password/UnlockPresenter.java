package com.example.a1809fiannce.main.more.password;

import android.util.Log;

import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.model.UnlockBean;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UnlockPresenter extends BasePresenter<IUnlockView> {
    public UnlockPresenter(IUnlockView iUnlockView) {
        attachView(iUnlockView);
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
                .setUnlockData(requestBody)
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
                            iView.onUnlockData(unlockBean);
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

    public void verifyUnlockData(String str){


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gPassword",str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreator.getFiannceApiService()
                .verifyUnlockData(requestBody)
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
                            iView.verifyUnlockData(unlockBean);
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

    public void clearUnlockData(String str){
        Log.i("wppp", "initData: "+str);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gPassword",str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreator.getFiannceApiService()
                .clearUnlockData(requestBody)
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
                            iView.clearUnlockData(unlockBean);
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
