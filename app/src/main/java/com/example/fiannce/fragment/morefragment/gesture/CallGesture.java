package com.example.fiannce.fragment.morefragment.gesture;

import com.example.framework.IBaseView;
import com.example.net.mode.GestureBean;

public interface CallGesture extends IBaseView {

    void onGestureData(GestureBean gestureBean);

    void onResetData(GestureBean gestureBean);

    void onVerityData(GestureBean gestureBean);
}
