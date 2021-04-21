package com.example.financial.base

data class Product<T>(
    val code: Int,
    val msg: String,
    val result: List<T>
)