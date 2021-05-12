package com.fiannce.bawei.gesturelock;

import com.fiannce.framework.IBaseView;
import com.fiannce.net.mode.GestureBean;

public interface IGestureView extends IBaseView {

    void onGestureData(GestureBean gestureBean);
    void verifyUnlockData(GestureBean gestureBean);
    void clearUnlockData(GestureBean gestureBean);
}
