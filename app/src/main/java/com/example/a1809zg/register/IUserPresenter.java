package com.example.a1809zg.register;

import com.example.frame.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.RegisterBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IUserPresenter extends BasePresenter<IUserView> {
    public IUserPresenter(IUserView iUserView) {
        attachView(iUserView);
    }
    public void UserPresenterData(String username,String password){
        RetrofitManager.getApi()
                .getReisterData(username, password)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                        mView.showLoaing();

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                     mView.hideLoading();
                    }
                })
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RegisterBean registerBean) {
                      mView.Regiter(registerBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
