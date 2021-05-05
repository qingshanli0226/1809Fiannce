package com.example.net_library.communication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

object ModuleCommunication {
    const val STAR_ACTIVITY_LOGIN="path/Login"
    const val STAR_ACTIVITY_MAIN="path/Main"
    private val map = HashMap<String, Class<*>>()

    //private var iUserInterface: IUserIntrface? = null
    //private var iAppendable: IAppIntrface? = null

    var context: Context? = null
    var intent: Intent? = null

    fun registeredActivity(path: String, blackActivity: Class<*>) {
        map.put(path, blackActivity)
    }

    fun build(paht: String): ModuleCommunication {
        intent = Intent(context, map.get(paht))
        return this
    }

    fun with(bundle: Bundle): ModuleCommunication {
        intent!!.putExtra("bundle", bundle)
        return this
    }

    fun star() {
        if (!(context is Activity)) {
            intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context!!.startActivity(intent)
    }

    fun init(context: Context) {
        this.context = context
    }




//    fun registeredAppInterface(iAppIntrface: IAppIntrface) {
//        this.iAppendable = iAppIntrface
//    }
//
//    fun getAppIntrface(): IAppIntrface {
//        return this.iAppendable!!
//    }
//
//    fun registeredAppInterface(iUserInterface: IUserIntrface) {
//        this.iUserInterface = iUserInterface
//    }
//
//    fun getIUserIntrface(): IUserIntrface {
//        return iUserInterface!!
//    }

//    interface IUserIntrface {
//        fun onStarLoginActivity(): IUserIntrface;
//        fun star(): IUserIntrface
//        fun with(bundle: Bundle): IUserIntrface
//        fun onStarRegisteredActivity(context: Context, bundle: Bundle);
//    }
//
//    interface IAppIntrface {
//        fun onStarMainActivity(context: Context, bundle: Bundle)
//    }
}