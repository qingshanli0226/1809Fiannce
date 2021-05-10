package com.example.a1809fiannce;

public
class CallImgPath {
    private static CallImgPath callImgPath;

    public static CallImgPath getInstance() {
        if (callImgPath==null){
            callImgPath=new CallImgPath();
        }
        return callImgPath;
    }
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
