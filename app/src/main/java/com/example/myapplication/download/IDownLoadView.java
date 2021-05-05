package com.example.myapplication.download;

import com.example.framework.IBaseView;

import okhttp3.ResponseBody;

public interface IDownLoadView extends IBaseView {

    void onDownLoad(ResponseBody responseBody);

}
