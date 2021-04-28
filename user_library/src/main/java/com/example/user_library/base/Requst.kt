package com.example.user_library.base

data class Requst<T>(
    val code: String,
    val message: String,
    val result: T
)