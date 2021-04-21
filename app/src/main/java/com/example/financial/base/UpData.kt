package com.example.financial.base

/**
 * "result": {
"version": "1.2",
"apkUrl": "http://49.233.93.155:9999/atguigu/apk/P2PInvest/app-debug.apk",
"desc": "解决一些bug, 优化网络请求!",
"force": true,
"versionCode": 2,
"apkHash": "59b2f08ca1598978c4f779e3e60ee90d"
}
 */
data class UpData(
    val apkHash: String,
    val apkUrl: String,
    val desc: String,
    val force: Boolean,
    val version: String,
    val versionCode: Int
)