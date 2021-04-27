package com.example.financial.ui.home

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.financial.R
import com.example.financial.base.ImageArr
import com.example.financial.base.Index
import com.example.financial.ulit.DataUlit
import com.example.frame_library.mvp.BaseFragment
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomePresenter>(), IHomeCanter.View {
    override fun bandLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        attaToBar(fragment_home_ToBar)
        attaPresenter(HomePresenter(HomeModle(), this))

    }

    override fun initData() {
        if (DataUlit.index != null) {
            onData(DataUlit.index!!)
        } else {
            mPresenter!!.getData()
        }
    }

    override fun OnLeftClickListenter() {

    }

    override fun OnTitleClickListenter() {

    }

    override fun OnRightClickListenter() {

    }

    //override fun setPresenter(): HomePresenter =HomePresenter(HomeModle(),this)

    override fun onData(index: Index) {
        initBanner(index.imageArr)

        fragment_home_title_text.text = index.proInfo.name
        fragment_home_progress.setProgress(index.proInfo.progress.toInt())
        fragment_home_samlltitle_text.text =
            getString(R.string.home_samlltitle_text) + index.proInfo.yearRate
    }

    private fun initBanner(list: List<ImageArr>) {
        fragment_home_banner.setImages(list)
            .setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                    var imageArr = path as ImageArr
                    Glide.with(context!!).load(imageArr.IMAURL).into(imageView as ImageView)
                }
            }).start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (fragment_home_progress != null) {
            fragment_home_progress.destroy()
        }
    }
}