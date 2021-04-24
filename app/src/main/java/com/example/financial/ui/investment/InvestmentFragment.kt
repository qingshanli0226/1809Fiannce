package com.example.financial.ui.investment

import androidx.fragment.app.Fragment
import com.example.financial.R
import com.example.financial.abper.FragmentAbper
import com.example.financial.ui.all.AllFramgnet
import com.example.frame_library.mvp.BaseFragment
import com.example.frame_library.mvp.IPresneter
import com.example.frame_library.view.ToBar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_investment.*

class InvestmentFragment:BaseFragment<IPresneter>() {
    override fun bandLayoutId(): Int= R.layout.fragment_investment

    override fun initView() {
        attaToBar(fragment_investment_ToBar)
        var arrayList = ArrayList<Fragment>()
        arrayList.add(AllFramgnet())
        arrayList.add(Fragment())
        arrayList.add(Fragment())
        fragment_investment_ViewPage.adapter=FragmentAbper(childFragmentManager,arrayList)

        fragment_investment_Tab.setupWithViewPager(fragment_investment_ViewPage)
        fragment_investment_Tab.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_STRETCH)
        fragment_investment_Tab.getTabAt(0)!!.setText(R.string.investment_title_all)
        fragment_investment_Tab.getTabAt(1)!!.setText(R.string.investment_title_recommend)
        fragment_investment_Tab.getTabAt(2)!!.setText(R.string.investment_title_Popular)
    }

    override fun initData() {

    }

    override fun OnLeftClickListenter() {
        TODO("Not yet implemented")
    }

    override fun OnTitleClickListenter() {
        TODO("Not yet implemented")
    }

    override fun OnRightClickListenter() {
        TODO("Not yet implemented")
    }

    override fun setPresenter(): IPresneter {
        TODO("Not yet implemented")
    }

}