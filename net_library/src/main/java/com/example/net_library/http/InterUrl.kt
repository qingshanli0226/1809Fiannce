package com.example.net_library.http

object InterUrl {
    const val IPADDRESS = "49.233.0.68"

    const val BASE_URL = "http://$IPADDRESS:8080/"

    const val PRODUCT = "atguigu/json/P2PInvest/product.json" //访问“全部理财”产品

    const val LOGIN = BASE_URL + "login" //登录

    const val INDEX = "atguigu/json/P2PInvest/index.json" //访问“homeFragment”

    const val USERREGISTER = "register" //访问“homeFragment”

    const val FEEDBACK = BASE_URL + "FeedBack" //注册

    const val UPDATE = "atguigu/json/P2PInvest/update.json" //更新应用
}