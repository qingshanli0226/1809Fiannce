package com.example.financial.ui.home

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.financial.R
import com.example.financial.base.ImageArr
import com.example.financial.base.Index
import com.example.financial.ulit.DataUlit
import com.example.frame_library.mvp.BaseFragment
import com.example.frame_library.mvp.IPresneter
import com.example.frame_library.view.ToBar
import com.youth.banner.loader.ImageLoader
import com.youth.banner.loader.ImageLoaderInterface
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment:BaseFragment<HomePresenter>(),HomeCanter.View {
    override fun bandLayoutId(): Int = R.layout.fragment_home

    override fun initView() {

    }

    override fun initData() {
        if (DataUlit.index != null) {
            onData(DataUlit.index!!)
        }else{
            mPresenter.getData()
        }
    }

    override fun OnLeftClickListenter() {
        TODO("Not yet implemented")
    }

    override fun OnTitleClickListenter() {
        Toast.makeText(activity,"点击标题",Toast.LENGTH_LONG).show()
    }

    override fun OnRightClickListenter() {
        TODO("Not yet implemented")
    }

    override fun setPresenter(): HomePresenter =HomePresenter(HomeModle(),this)

    override fun onData(index: Index) {
        initBanner(index.imageArr)
    }

    private fun initBanner(list: List<ImageArr>){
        fragment_home_banner.setImages(list)
            .setImageLoader(object :ImageLoader(){
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                    var imageArr = path as ImageArr
                    Glide.with(context!!).load(imageArr.IMAURL).into(imageView as ImageView)
                }
            }).start()
    }

    override fun initToBar(): ToBar {
        return fragment_home_ToBar
    }
}