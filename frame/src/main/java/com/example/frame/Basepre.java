package com.example.frame;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class Basepre<V>{
    private List<Disposable> list=new ArrayList<>();
    protected V mView;

    public void attchview(V mView) {
        this.mView = mView;
    }
    public synchronized void add(Disposable disposable){
        list.add(disposable);
    }
    public void deachView(){
        this.mView=null;
        for (Disposable disposable:list
             ) {
            if (disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
