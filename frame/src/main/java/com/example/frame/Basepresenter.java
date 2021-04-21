package com.example.frame;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class Basepresenter<V> {
    private List<Disposable> disposableList=new ArrayList<>();
    public synchronized void  add(Disposable disposable){
        disposableList.add(disposable);
    }
    protected V mView;
    public void attachView(V mView){
        this.mView=mView;
    }
    public void detachView(){
        this.mView=null;
        for (Disposable disposable:disposableList) {
            if (disposable.isDisposed()){
                disposable.dispose();
            }

        }
    }
}
