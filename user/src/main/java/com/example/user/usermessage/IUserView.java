package com.example.user.usermessage;


import com.example.framework.IBaseView;
import com.example.net.model.UnlockBean;

public interface IUserView extends IBaseView {

    void onUserData(UnlockBean unlockBean);

}
