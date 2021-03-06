package com.example.a1809fiannce.Gesture;

import com.blankj.utilcode.util.LogUtils;
import com.example.framwork.base.BasePresenter;
import com.example.network.model.GesturePwd;
import com.example.network.retrofit.RetrofitManager;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GesturePresenter extends BasePresenter<CallGesture> {

    public GesturePresenter(CallGesture callGesture) {
        addThouView(callGesture);
    }

    public void GestureData(String aPwd){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gPassword",aPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitManager.getRetrofit()
                .setGesturePwd(requestBody)
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
    public void ResetPwd(String aPwd){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gPassword",aPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitManager.getRetrofit()
                .setReset(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<GesturePwd>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GesturePwd gesturePwd) {
                        if (iView!=null){
                            iView.OnResetData(gesturePwd);
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

    public void VerityPwd(String aPwd){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gPassword",aPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitManager.getRetrofit()
                .setVerify(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GesturePwd>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GesturePwd gesturePwd) {
                        if (iView!=null){
                            LogUtils.json(gesturePwd);
                            iView.OnVerityData(gesturePwd);
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
