package com.example.financial.ui.first

import com.example.frame_library.mvp.IModle
import com.example.frame_library.mvp.IView

interface FirstCanter {
    interface Modle :IModle{
        fun getBanner()
    }
    interface VIew :IView{}
}