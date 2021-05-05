package com.example.financial.ui.mine

import android.widget.Toast
import com.example.financial.R
import com.example.frame_library.mvp.BaseFragment
import com.example.frame_library.mvp.IPresneter
import com.example.net_library.communication.ModuleCommunication
import com.example.net_library.User
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment<IPresneter>(), User.IUserLoginListener {
    override fun bandLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
        attaToBar(fragment_mine_ToBar)
        if (!User.long) {
            User.registeredUserLoginListener(this)
            ModuleCommunication.build(ModuleCommunication.STAR_ACTIVITY_LOGIN).star()
        }
    }

    override fun initData() {

    }

    override fun OnLeftClickListenter() {

    }

    override fun OnTitleClickListenter() {

    }

    override fun OnRightClickListenter() {

    }

//    override fun onLogin(b: Boolean) {
//        Toast.makeText(activity,"$b",Toast.LENGTH_LONG).show()
//    }

    override fun onUserLoginChange(b: Boolean) {
        Toast.makeText(activity,"$b",Toast.LENGTH_LONG).show()
    }

//    override fun setPresenter(): IPresneter {
//        TODO("Not yet implemented")
//    }
}