package com.example.a1809fiannce.main.more.password;


import com.example.framework.IBaseView;
import com.example.net.model.UnlockBean;

public interface IUnlockView extends IBaseView {

    void onUnlockData(UnlockBean unlockBean);
    void verifyUnlockData(UnlockBean unlockBean);
    void clearUnlockData(UnlockBean unlockBean);


}
