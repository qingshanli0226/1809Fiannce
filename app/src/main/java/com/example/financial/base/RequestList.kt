package com.example.financial.base

/**
 *
"code": 200,
"msg": "请求成功",

"result": {}
 */
data class RequestList<T>(
    val code: Int,
    val msg: String,
    val result: List<T>
)