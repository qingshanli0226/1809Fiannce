package com.example.user_library.login

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.frame_library.mvp.BaseActitvty
import com.example.frame_library.mvp.IPresneter
import com.example.net_library.evenuilt.Meanger
import com.example.net_library.evenuilt.Uilt
import com.example.user_library.R
import com.example.user_library.base.Requst
import com.example.user_library.base.User
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoginActivity : BaseActitvty<LoginPresenter>() ,View.OnClickListener,ILoginCanter.View{
    override fun bandLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        act_login_but.setOnClickListener(this)
        attaPresenter(LoginPresenter(this,LoginModle()))
    }

    override fun initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun OnMeanger(meanger: Meanger) {
        if (meanger.Flag.equals(Uilt.ACTVON_LOGIN)) {
            Toast.makeText(this, meanger.Meang, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.act_login_but->{
                mPresenter!!.login()
            }
        }
    }

    override fun getUsername(): String {
        return act_login_username_eTest.text.toString()
    }

    override fun getPassword(): String {
        return act_login_password_eTest.text.toString()
    }

    override fun onRequst(requst: Requst<User>) {
        Toast.makeText(this,requst.message,Toast.LENGTH_LONG).show()
        finish()
    }

//    override fun setPresneter(): IPresneter {
//        TODO("Not yet implemented")
//    }

}