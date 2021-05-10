package com.example.a1809fiannce.Gesture;

import com.example.framwork.IView;
import com.example.network.model.GesturePwd;

public interface CallGesture extends IView {
    void OnGestureData(GesturePwd gesturePwd);

    void OnResetData(GesturePwd gesturePwd);

    void OnVerityData(GesturePwd gesturePwd);


}
