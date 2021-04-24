package com.example.fiannce.fragment.fragment_invest;

import android.util.Log;

import com.example.fiannce.bean.BeanBack;
import com.example.framework.BasePresenter;
import com.example.framework.manager.RetrofitManager;
import com.example.net.mode.AllBean;
import com.example.net.mode.HomeBean;
import com.example.net.mode.UpdateBean;
import com.example.net.mode.VersionBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UpdatePresenter_AllFragment extends BasePresenter<BeanBack> {

    public UpdatePresenter_AllFragment(BeanBack callBack) {
        attachView(callBack);
    }

    public void HomeData(){
        RetrofitManager.getRetrofit()
                .getHomeData()
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
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("zx", "onSubscribe: 开始");
                        iView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                        if (iView!=null){
                            iView.HomeData(homeBean);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.showError(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void UpdateData(){
        RetrofitManager.getRetrofit()
                .getServerVersion()
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
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<VersionBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        iView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull VersionBean versionBean) {

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

    public void AllData(){
        RetrofitManager.getRetrofit()
                .AllData()
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
                        iView.hideLoading();
                    }
                })
                .subscribe(new Observer<AllBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                        iView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull AllBean allBean) {
                        iView.AllData(allBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        iView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
