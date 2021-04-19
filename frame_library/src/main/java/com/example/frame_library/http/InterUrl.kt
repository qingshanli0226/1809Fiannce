package com.example.frame_library.http

object InterUrl {
    const val IPADDRESS = "49.233.0.68"

    const val BASE_URL = "http://$IPADDRESS:8080/"
    const val PRODUCT = BASE_URL + "atguigu/json/P2PInvest/product.json" //访问“全部理财”产品


    const val LOGIN = BASE_URL + "login" //登录


    const val INDEX = BASE_URL + "atguigu/json/P2PInvest/index.json" //访问“homeFragment”


    const val USERREGISTER = BASE_URL + "register" //访问“homeFragment”


    const val FEEDBACK = BASE_URL + "FeedBack" //注册


    const val UPDATE = "atguigu/json/P2PInvest/update.json" //更新应用


}