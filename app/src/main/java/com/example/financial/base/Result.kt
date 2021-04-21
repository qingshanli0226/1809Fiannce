package com.example.financial.base

data class Result(
    val apkHash: String,
    val apkUrl: String,
    val desc: String,
    val force: Boolean,
    val version: String,
    val versionCode: Int
)