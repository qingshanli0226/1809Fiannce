package com.example.framework;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BasePresenter<V> {

    protected List<Disposable> disposableList=new ArrayList<>();

    public void add(Disposable disposable){
        if (disposableList!=null){
            disposableList.add(disposable);
        }
    }
    protected V mView;

    public void attView(V mview){
        this.mView=mview;
    }

    public void destroy(){
        if (mView!=null){
            mView=null;
        }
        if (disposableList!=null){
            for (int i = 0; i < disposableList.size(); i++) {
                disposableList.get(i).dispose();
            }
        }
    }
}
