package com.example.network;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {

    private List<Disposable> list=new ArrayList<>();
    protected V iView;

    public synchronized void addView(Disposable disposable){
        list.add(disposable);
    }
    public void addThouView(V iView){
        this.iView=iView;
    }
    public void delView(){
        iView=null;
        for (Disposable disposable : list) {
            if (disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
    
}
