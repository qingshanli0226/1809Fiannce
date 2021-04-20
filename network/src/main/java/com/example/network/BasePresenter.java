package com.example.network;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {

    private List<Disposable> list=new ArrayList<>();
    protected V view;

    public synchronized void addView(Disposable disposable){
        list.add(disposable);
    }

    public void delView(){
        view=null;
        for (Disposable disposable : list) {
            if (disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
    
}
