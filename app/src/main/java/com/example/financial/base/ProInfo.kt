package com.example.financial.base

/**
 * "proInfo": {
"id": "1",
"memberNum": "100",
"minTouMoney": "100",
"money": "10",
"name": "硅谷彩虹新手计划",
"progress": "90",
"suodingDays": "30",
"yearRate": "8.00"
}
 */
data class ProInfo(
    val id: String,
    val memberNum: String,
    val minTouMoney: String,
    val money: String,
    val name: String,
    val progress: String,
    val suodingDays: String,
    val yearRate: String
)