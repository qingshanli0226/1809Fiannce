package com.example.a1809fiannce;

public class CallUpdate {
    private static CallUpdate callUpdate;

    public static CallUpdate getInstance() {
        if (callUpdate==null){
            callUpdate=new CallUpdate();
        }
        return callUpdate;
    }
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
