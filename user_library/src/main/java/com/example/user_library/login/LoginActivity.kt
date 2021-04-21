package com.example.user_library.login

import android.widget.Toast
import com.example.frame_library.mvp.BaseActitvty
import com.example.frame_library.mvp.IPresneter
import com.example.net_library.evenuilt.Meanger
import com.example.net_library.evenuilt.Uilt
import com.example.user_library.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoginActivity :BaseActitvty<IPresneter>() {
    override fun bandLayoutId(): Int= R.layout.activity_login

    override fun initView() {
        EventBus.getDefault().register(this)
        EventBus.getDefault().post(Meanger(Uilt.ACTVON_LOGIN, "跳转成功，事件发送Toast"))
    }

    override fun initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun OnMeanger(meanger: Meanger){
        if (meanger.Flag.equals(Uilt.ACTVON_LOGIN)){
            Toast.makeText(this,meanger.Meang,Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun setPresneter(): IPresneter {
        TODO("Not yet implemented")
    }

}