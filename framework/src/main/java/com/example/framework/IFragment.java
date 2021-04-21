package com.example.framework;

import android.view.View;

public interface IFragment {
    <T extends View> T findViewById(int resId);
}
