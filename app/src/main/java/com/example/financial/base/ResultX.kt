package com.example.financial.base

data class ResultX(
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
        return "ResultX(id='$id', memberNum='$memberNum', minTouMoney='$minTouMoney', money='$money', name='$name', progress='$progress', suodingDays='$suodingDays', yearRate='$yearRate')"
    }
}