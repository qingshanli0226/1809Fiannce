package com.example.framework;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {
    private List<Disposable> list = new ArrayList<>();
    public void add(Disposable disposable){
        list.add(disposable);
    }

    private V iView;
    public void attchView(V iView){
        this.iView = iView;
    }

    public void detachView(){
        this.iView = null;
        for (Disposable disposable : list) {
            if(disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
