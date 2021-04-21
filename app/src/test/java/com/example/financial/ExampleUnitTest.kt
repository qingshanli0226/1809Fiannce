package com.example.financial

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var j:Int=1
    val i:Int by lazy {
        print("===")
        1
    }
    @Test
    fun addition_isCorrect() {
        print(i)
        print(i)
        print(i)
        print(i)
        print(j)
    }
}