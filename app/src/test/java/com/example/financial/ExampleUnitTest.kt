package com.example.financial

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    public fun addition_isCorrect() {
        TestCalss().name="llll"
    }
}

class TestCalss {
    var name: String
        get():String = name
        set(name: String) {
            this.name = name
            print("1111")
        }
}