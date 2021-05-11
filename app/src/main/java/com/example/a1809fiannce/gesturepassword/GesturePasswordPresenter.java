package com.example.a1809fiannce.gesturepassword;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.net.RetrofitCreator;
import com.example.net.mode.RegisterBean;


import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class GesturePasswordPresenter extends BasePresenter<IGesturePassword> {

    public GesturePasswordPresenter(IGesturePassword iGesturePassword) {
        attachView(iGesturePassword);
    }

    public void getGesturePassword(String gPassword){
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("productId", productId);
//            jsonObject.put("productNum", productNum);
//            jsonObject.put("productName", productName);
//            jsonObject.put("url", url);
//            jsonObject.put("productPrice", productPrice);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//       /* //使用MD5生成签名信息
//        String sign = SignUtil.generateJsonSign(jsonObject);
//        //将签名信息放到参数列表中
//        jsonObject.put("sign",sign);
//
//        SignUtil.encryptJsonParamsByBase64(jsonObject);*/
//
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gPassword",gPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RetrofitCreator.getFiannceApiService()
                .getGesturePassword(responseBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                        LogUtils.json(registerBean);
                        if (iView!=null){
                            iView.onGesturePassword(registerBean);
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
