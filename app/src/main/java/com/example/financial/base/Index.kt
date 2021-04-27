package com.example.financial.base

/**
 * "result": {

"imageArr": [.. 4 Items ..],

"proInfo": {.. 8 Items ..}
}
 */
data class Index(
    val imageArr: List<ImageArr>,
    val proInfo: ProInfo,
)