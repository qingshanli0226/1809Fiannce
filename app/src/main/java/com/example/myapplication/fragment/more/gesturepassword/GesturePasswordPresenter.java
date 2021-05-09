package com.example.myapplication.fragment.more.gesturepassword;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.model.GesturePasswordBean;
import com.example.net.RetrofitCretor;
import com.google.gson.Gson;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GesturePasswordPresenter extends BasePresenter<IGesturePasswordView> {

    public GesturePasswordPresenter(IGesturePasswordView iGesturePasswordView) {
        attachView(iGesturePasswordView);
    }

    public void postGesturePassword(Map<String,String> map){

        String s = new Gson().toJson(map);
        MediaType parse = MediaType.parse("application/json;charset=UTF-8");
        RequestBody requestBody = RequestBody.create(parse, s);

        RetrofitCretor.getFiannceApiService()
                .setGesturePassword(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GesturePasswordBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull GesturePasswordBean gesturePasswordBean) {
                        if (IView!=null){
                            LogUtils.json(gesturePasswordBean);
                            IView.getGesturePassword(gesturePasswordBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (IView!=null){
                            IView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
