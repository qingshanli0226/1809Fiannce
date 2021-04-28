package com.example.net_library.evenuilt

import android.content.Context

object Uilt {

    const val ACTVON_LOGIN: String = "log.login"

}

data class Meanger(var Flag: String, var Meang: String) {}

class ContextInstrument {
    companion object {
        var context: Context? = null
    }
}