package com.finance.framework;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {
    private final List<Disposable> disposablesList = new ArrayList<>();

    public synchronized void add(Disposable disposable){
        disposablesList.add(disposable);
    }

    protected V iView;

    public void attachView(V iView){
        this.iView = iView;
    }

    public void detachView(){
        //当页面销毁时，需要解除关联，避免内存泄漏
        this.iView = null;

        //当页面销毁时，将未结束的线程停掉，否则会影响性能
        for (Disposable disposable : disposablesList) {
            if (disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
