package com.example.net_library

object User {
    var name: String = ""
    var long: Boolean = false
        set(value) {
            field = value
            onUserLoginChange(value)
        }
    var token:String=""

    private val list = ArrayList<IUserLoginListener>()

    fun registeredUserLoginListener(iUserLoginListener: IUserLoginListener) {
        list.add(iUserLoginListener)
    }

    fun unRegisteredUserLoginListener(iUserLoginListener: IUserLoginListener){
        list.remove(iUserLoginListener)
    }

    fun onUserLoginChange(b: Boolean) {
        for (iLoginIntrface in list) {
            iLoginIntrface.onUserLoginChange(b)
        }
    }

    interface IUserLoginListener {
        fun onUserLoginChange(b: Boolean);
    }
}