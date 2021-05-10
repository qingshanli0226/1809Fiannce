package com.example.gitproject.more.password;

import com.example.framework.IBaseView;
import com.example.net.bean.GesturePassword;

public interface IGestureView extends IBaseView {
    void onSetGesture(GesturePassword gesturePassword);
    void onLoginGesture(GesturePassword gesturePassword);
    void onClearGesture(GesturePassword gesturePassword);
}
