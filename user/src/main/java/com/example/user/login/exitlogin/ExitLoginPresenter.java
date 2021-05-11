package com.example.user.login.exitlogin;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BasePresenter;
import com.example.model.ExitLoginBean;
import com.example.net.RetrofitCretor;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ExitLoginPresenter extends BasePresenter<IExitLoginView> {

    public ExitLoginPresenter(IExitLoginView iExitLoginView) {
        attachView(iExitLoginView);
    }

    public void exitLogin(){
        RetrofitCretor.getFiannceApiService()
                .postExitLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ExitLoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        add(d);
                    }

                    @Override
                    public void onNext(@NonNull ExitLoginBean exitLoginBean) {
                        if (IView!=null){
                            IView.onExitLoginData(exitLoginBean);
                            LogUtils.json(exitLoginBean);
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
