package com.example.financial.ui

import android.widget.RadioGroup
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
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = DataUlit.AROUTER_JUMP_MAIN)
class MainActivity : BaseActitvty<IPresneter>() ,RadioGroup.OnCheckedChangeListener{
    private val fragmentManger:FragmentManger by lazy {
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
    override fun setPresneter(): IPresneter {
        TODO("Not yet implemented")
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
       when(checkedId){
           R.id.act_main_radio_home ->{ fragmentManger.setCheckIteam(0) }
           R.id.act_main_radio_investment ->{ fragmentManger.setCheckIteam(1)}
           R.id.act_main_radio_mine ->{fragmentManger.setCheckIteam(2)}
           R.id.act_main_radio_mroe ->{fragmentManger.setCheckIteam(3)}
       }
    }
}