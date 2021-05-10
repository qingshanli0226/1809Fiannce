package com.fiannce.bawei.gesturelock;

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
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class GesturePresenter extends BasePresenter<IGestureView> {

    public GesturePresenter(IGestureView iGestureView) {
        attachView(iGestureView);
    }


    public void postGesturePassword(Map<String,String> map){

        String s = new Gson().toJson(map);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);

        RetrofitCreator.getFiannceApiService()
                .setPassword(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GestureBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull GestureBean gesturePasswordBean) {
                        if (iView != null){
                            iView.onGestureData(gesturePasswordBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
