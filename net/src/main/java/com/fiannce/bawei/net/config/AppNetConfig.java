package com.fiannce.bawei.net.config;

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

    public static final String PRODUCT = BASE_URL + "atguigu/json/P2PInvest/product.json";//访问“全部理财”产品

    public static final String LOGIN = BASE_URL + "login";//登录

    public static final String INDEX = BASE_URL + "atguigu/json/P2PInvest/index.json";//访问“homeFragment”

    public static final String USERREGISTER = BASE_URL + "register";//访问“homeFragment”

    public static final String FEEDBACK = BASE_URL + "FeedBack";//注册

    public static final String UPDATE = BASE_URL + "atguigu/json/P2PInvest/update.json";//更新应用
}