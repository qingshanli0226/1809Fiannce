package com.example.frame_library.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentManger(resd:Int,var fragmentManger:FragmentManager,var list: ArrayList<Fragment>) {

    init {
        var beginTransaction = fragmentManger.beginTransaction()
        for (i:Int in 0..list.size-1){
            beginTransaction.add(resd,list.get(i))

        }
        beginTransaction.commit()

    }

    fun setCheckIteam(posion:Int){
        var beginTransaction = fragmentManger.beginTransaction()
        for (i:Int in 0..list.size-1){
            if (i==posion){
                beginTransaction.show(list.get(i))
            }else{
                beginTransaction.hide(list.get(i))
            }
        }
        beginTransaction.commit()
    }
}