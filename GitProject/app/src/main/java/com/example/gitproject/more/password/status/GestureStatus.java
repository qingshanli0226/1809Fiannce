package com.example.gitproject.more.password.status;

import com.example.net.bean.GesturePassword;

public class GestureStatus {
    private static GestureStatus gestureStatus;
    private GestureStatus(){

    }
    public static GestureStatus getInstance() {
        if(gestureStatus == null){
            gestureStatus = new GestureStatus();
        }
        return gestureStatus;
    }
    private int pwdStatus = -1;

    public int getPwdStatus() {
        return pwdStatus;
    }

    public void setPwdStatus(int pwdStatus) {
        this.pwdStatus = pwdStatus;
    }

}
