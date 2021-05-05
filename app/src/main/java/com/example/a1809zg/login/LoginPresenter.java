package com.example.a1809zg.login;

import com.example.frame.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.LoginBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<ILoginView> {
    public LoginPresenter(ILoginView iLoginView) {
        attachView(iLoginView);
    }
    public void LoginData(String name,String password){
        RetrofitManager.getApi()
                .getLoginData(name, password)
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
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginBean loginBean) {
                          mView.Login(loginBean);
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
