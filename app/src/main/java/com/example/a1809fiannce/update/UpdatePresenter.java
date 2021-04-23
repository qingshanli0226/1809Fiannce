package com.example.a1809fiannce.update;

import android.util.Log;

import com.example.a1809fiannce.CallBack;
import com.example.formwork.RetrofitManager;
import com.example.formwork.model.AllBean;
import com.example.formwork.model.HomeBean;
import com.example.formwork.model.UpdateBean;
import com.example.network.BasePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UpdatePresenter extends BasePresenter<CallBack> {
    public UpdatePresenter(CallBack callBack) {
        addThouView(callBack);
    }

    public void HomeData(){
        RetrofitManager.getRetrofit()
                .HomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                            addView(disposable);
                            iView.ShowLoading();
                        }
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView!=null){
                            iView.HideLoading();
                        }
                    }
                })
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

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
                            iView.Error(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                }

                );
    }
    public void UpdateData(){
        RetrofitManager.getRetrofit()
                .UpdateData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (iView!=null){
                            iView.ShowLoading();
                            addView(disposable);

                        }

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView!=null){
                            iView.HideLoading();
                        }
                    }
                })
                .subscribe(new Observer<UpdateBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpdateBean updateBean) {
                            if (iView!=null){
                                iView.UpdateData(updateBean);
                            }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (iView!=null){
                            iView.Error(e.getMessage());
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
                        if (iView!=null){
                            iView.ShowLoading();
                            addView(disposable);
                        }

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (iView!=null){
                            iView.HideLoading();
                        }

                    }
                })
                .subscribe(new Observer<AllBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AllBean allBean) {
                        iView.AllData(allBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        iView.Error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
