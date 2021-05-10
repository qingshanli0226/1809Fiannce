package com.example.user.usermessage;

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

public class UserPresenter extends BasePresenter<IUserView> {
    public UserPresenter(IUserView iUserView) {
        attachView(iUserView);
    }

    public void getUnlockData(){
        RetrofitCreator.getFiannceApiService()
                .getLoginOut()
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
                            iView.onUserData(unlockBean);
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
