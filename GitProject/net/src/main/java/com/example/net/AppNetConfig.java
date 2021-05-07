package com.example.net;

/**
 * Created by shkstart on 2016/12/2 0002.
 * 配置网络请求相关的地址
 */
public class AppNetConfig {

    public static final String IPADDRESS = "49.233.0.68";

    public static final String BASE_URL = "http://" + IPADDRESS + ":8080/";
//如下的IPADDRESS可直接访问尚有道后台的服务器及数据库，不用在本地安装tomcat及mysql数据库
//    public static final String IPADDRESS = "182.92.5.3";
//
//    public static final String BASE_URL = "http://" + IPADDRESS + ":8081/P2PInvest/";

    public static final String PRODUCT = "atguigu/json/P2PInvest/product.json";//访问“全部理财”产品

    public static final String LOGIN = "login";//登录

    public static final String INDEX = "atguigu/json/P2PInvest/index.json";//访问“homeFragment”

    public static final String USERREGISTER = "register";//注册
    public static final String AUTOLOGIN = "autoLogin";//自动登录

    public static final String FEEDBACK = "FeedBack";

    public static final String SETGESTUREPASSWORD = "setGesturePassword";
    public static final String LOGINGESTUREPASSWORD = "loginByGesturePassword";
    public static final String CLEARESTUREPASSWORD = "clearByGesturePassword";



    public static final String UPDATE = "atguigu/json/P2PInvest/update.json";//更新应用


}
