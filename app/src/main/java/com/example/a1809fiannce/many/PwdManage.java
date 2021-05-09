package com.example.a1809fiannce.many;

public class PwdManage {


    private static PwdManage manage;

    public static PwdManage getManage() {
        if (manage==null){
            manage=new PwdManage();
        }
        return manage;
    }

    private boolean isPwd=true;

    public boolean isPwd() {
        return isPwd;
    }

    public void setPwd(boolean pwd) {
        isPwd = pwd;
    }
}
