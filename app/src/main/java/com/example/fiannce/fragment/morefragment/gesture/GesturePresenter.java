package com.example.fiannce.fragment.morefragment.gesture;

import com.example.framework.BasePresenter;
import com.example.framework.manager.RetrofitManager;
import com.example.net.mode.GestureBean;

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

    public GesturePresenter(CallGesture callGesture){
        attachView(callGesture);
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
                .subscribe(new Observer<GestureBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GestureBean gestureBean) {
                        if (iView != null){
                            iView.onGestureData(gestureBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView != null){
                            iView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
