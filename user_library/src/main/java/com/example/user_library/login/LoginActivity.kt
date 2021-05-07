package com.example.user_library.login
import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.example.frame_library.mvp.BaseActitvty
import com.example.net_library.communication.ModuleCommunication
import com.example.user_library.R
import com.example.user_library.base.Requst
import com.example.user_library.base.User
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus

class LoginActivity : BaseActitvty<LoginPresenter>() ,View.OnClickListener,ILoginCanter.View{
    override fun bandLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        act_login_but.setOnClickListener(this)
        attaPresenter(LoginPresenter(this,LoginModle()))

    }

    override fun initData() {

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
        if (requst.code.equals("200")){
            com.example.net_library.User.long=true
            com.example.net_library.User.name=requst.result.name
            com.example.net_library.User.token=requst.result.token
            com.example.net_library.User.long=true
            SPUtils.getInstance().put("token",requst.result.token)
            ModuleCommunication.build(ModuleCommunication.STAR_ACTIVITY_MAIN).star()
        }
    }


}