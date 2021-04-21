package com.fiance.framework;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {

    private List<Disposable> disposableList=new ArrayList<>();

    public synchronized void add(Disposable disposable){
        disposableList.add(disposable);
    }
    protected V iView;

    public void attachView(V iView) {
        this.iView = iView;

    }
    public void detchView(){
        this.iView=null;

        for(Disposable disposable:disposableList){
            if (disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
