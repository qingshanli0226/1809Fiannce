package com.example.framework;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {
    private List<Disposable> disposableList = new ArrayList<>();

    public synchronized void add(Disposable disposable){
        disposableList.add(disposable);
    }

    protected V IView;

    public void attachView(V IView) {
        this.IView = IView;
    }

    public void detachView(){
        this.IView = null;
        for (Disposable disposable : disposableList) {
            if (disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
