package com.example.financial.base

/**
 * {
"id": "1",
"name": "新手福利计划",
"money": "10",
"yearRate": "8.00",
"suodingDays": "30",
"minTouMoney": "100",
"memberNum": "100",
"progress": "50"
}
 */
data class Product(
    val id: String,
    val memberNum: String,
    val minTouMoney: String,
    val money: String,
    val name: String,
    val progress: String,
    val suodingDays: String,
    val yearRate: String

) {
    override fun toString(): String {
        return "Product(id='$id', memberNum='$memberNum', minTouMoney='$minTouMoney', money='$money', name='$name', progress='$progress', suodingDays='$suodingDays', yearRate='$yearRate')"
    }
}