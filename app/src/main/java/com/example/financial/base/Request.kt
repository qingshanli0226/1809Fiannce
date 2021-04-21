package com.example.financial.base

data class Request<T>(
    val code: Int,
    val msg: String,
    val result: T
)