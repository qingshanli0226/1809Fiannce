package com.example.financial.ui

import android.view.KeyEvent
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.financial.R
import com.example.financial.ui.mine.MineFragment
import com.example.financial.ui.home.HomeFragment
import com.example.financial.ui.investment.InvestmentFragment
import com.example.financial.ui.mroe.MroeFragment
import com.example.financial.ulit.DataUlit
import com.example.frame_library.mvp.BaseActitvty
import com.example.frame_library.mvp.IPresneter
import com.example.frame_library.view.FragmentManger
import com.example.net_library.communication.ModuleCommunication
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = DataUlit.AROUTER_JUMP_MAIN)
class MainActivity : BaseActitvty<IPresneter>(), RadioGroup.OnCheckedChangeListener {
    private val fragmentManger: FragmentManger by lazy {
        var arrayList = ArrayList<Fragment>()
        arrayList.add(HomeFragment())
        arrayList.add(InvestmentFragment())
        arrayList.add(MineFragment())
        arrayList.add(MroeFragment())

        FragmentManger(R.id.act_main_container, supportFragmentManager, arrayList)
    }

    override fun bandLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

        fragmentManger

        act_main_radiogroup.setOnCheckedChangeListener(this)


    }

    override fun initData() {

    }

//    override fun setPresneter(): IPresneter {
//
//    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.act_main_radio_home -> {
                fragmentManger.setCheckIteam(0)
            }
            R.id.act_main_radio_investment -> {
                fragmentManger.setCheckIteam(1)
            }
            R.id.act_main_radio_mine -> {
                fragmentManger.setCheckIteam(2)
            }
            R.id.act_main_radio_mroe -> {
                fragmentManger.setCheckIteam(3)
            }
        }
    }

    private var tiem = 0L
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - tiem > 2000) {
                Toast.makeText(this, "双击退出", Toast.LENGTH_LONG).show()
                tiem = System.currentTimeMillis()
                return false
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}