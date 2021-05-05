package com.example.financial

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.financial.tesst.t
import com.example.financial.tesst

internal class tesst : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return t()
    }

    internal inner class t : Binder() {
        fun get(): tesst {
            return this@tesst
        }
    }
}