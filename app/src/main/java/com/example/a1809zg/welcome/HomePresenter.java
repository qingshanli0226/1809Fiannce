package com.example.a1809zg.welcome;

import com.example.frame.BasePresenter;
import com.example.net.RetrofitManager;
import com.example.net.bean.HomeBean;
import com.example.net.bean.UpdataBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<IHomeView> {
    public HomePresenter(IHomeView IHomeView){
        attachView(IHomeView);
    }
    public void getHomeData(){
        RetrofitManager.getApi()
                .getHomeData()
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
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                      if (mView!=null){
                          mView.onHomeData(homeBean);
                      }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                       if (mView!=null){
                           mView.showError(e.getMessage());
                       }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getVersionData() {
        RetrofitManager.getApi()
                .getUpdataData()
                .delay(2,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {//该函数当RxJava发起网络请求时调用
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        add(disposable);//将disposable存储起来，当页面销毁时，可以通过它去判断当前获取数据的线程是否已经停止，如果没有停止，则停止
                        mView.showLoaing();
                    }
                })
                .doFinally(new Action() {//当网络请求结束时回调该方法
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Observer<UpdataBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UpdataBean updataBean) {
                        if (mView!=null){
                            mView.onUpdaData(updataBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (mView!=null){
                            mView.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    }




