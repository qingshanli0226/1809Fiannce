package com.fiannce.bawei.gesturelock;

import com.fiannce.framework.BasePresenter;
import com.fiannce.net.RetrofitCreator;
import com.fiannce.net.mode.GestureBean;
import com.fiannce.net.mode.HomeBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

//    JSONObject jsonObject = new JSONObject();
//        try{
//            jsonObject.put("productId", productId);
//            jsonObject.put("productNum", productNum);
//            jsonObject.put("productName", productName);
//            jsonObject.put("url", url);
//            jsonObject.put("productPrice", productPrice);
//        } catch(JSONException e){
//            e.printStackTrace();
//        }


//    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

//    public void setPassword() {
//        RetrofitCreator.getFiannceApiService()
//                .setPassword()
//                .delay(2, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        add(disposable);
//                        iView.showLoading();
//                    }
//                })
//                .doFinally(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        iView.hideLoading();
//                    }
//                })
//                .subscribe(new Observer<GestureBean>() {
//                    @Override
//                    public void onSubscribe(Disposable disposable) {
//
//                    }
//
//                    @Override
//                    public void onNext(GestureBean gestureBean) {
//                        if (iView != null) {
//                            iView.onGestureData(gestureBean);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        if (iView != null) {
//                            iView.showToast(throwable.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}
