package com.example.user.autologin;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.model.AutoLoginBean;
import com.example.model.LoginBean;
import com.example.net.RetrofitCretor;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PersonAutoLoginPresenter extends BasePresenter<IPersonLoginAutoView> {

    public PersonAutoLoginPresenter(IPersonLoginAutoView iPersonLoginAutoView) {
        attachView(iPersonLoginAutoView);
    }

    public void postAutoLogin(String token){
        RetrofitCretor.getFiannceApiService()
                .postAutoLogin(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (IView!=null){
                            IView.hideLoading();
                        }
                    }
                })
                .subscribe(new Observer<AutoLoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AutoLoginBean autoLoginBean) {
                        if (IView!=null){
                            IView.autoLogin(autoLoginBean);
                            LogUtils.json(autoLoginBean);
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
