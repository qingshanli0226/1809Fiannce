package com.example.financial.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.example.financial.MainActivity
import com.example.financial.R
import com.example.financial.base.Product
import com.example.financial.base.ResultX
import com.example.financial.ui.product.ProductFragment
import com.example.financial.ui.product.ProductModle
import com.example.financial.ui.product.ProductPresenter
import com.example.financial.ulit.DataUlit
import com.example.frame_library.mvp.BaseActitvty
import kotlinx.android.synthetic.main.home_activity.*
import java.util.*
import kotlin.concurrent.timerTask

class HomeActivity:BaseActitvty<HomePresenter>(),HomeCanter.View {
    private val LODINDATA:Int=-10001
    private val UPDATA:Int=-10002
    private val TIME:Int=-10003
    private val handler:Handler=object :Handler(){
        private var ISLODINDATA:Boolean=false
        private var ISUPDATA:Boolean=false
        private var ISTIME:Boolean=false
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when(msg.what){
                LODINDATA->ISLODINDATA=true
                UPDATA->ISUPDATA=true
                TIME->ISTIME=true
            }

            if (ISLODINDATA&&ISTIME&&ISUPDATA){
                UpData()
            }
        }
    }

    /***
     *三秒倒计时
     */
    private val timer:Timer by lazy {
        var time:Int =3
        var timer1 = Timer()
        timer1.schedule(timerTask {
            if (time == 0) {
                handler.sendEmptyMessage(TIME)
                cancel()
            }else{
            handler.post {
                act_home_time_text.setText("${time}")
            }
            time--
            }

        }, 1000, 1000)
        timer1
    }

    override fun bandLayoutId(): Int = R.layout.home_activity

    override fun initView() {

    }

    override fun initData() {
        act_home_version_text.setText(getVersionName()+"")

        mPresenter!!.getVersion()

        timer

        mPresenter.LoginData()
    }

    /**
     *获取当前版本名称
     *  @return 以String形式返回版本名称
    */
     fun getVersionName(): String? {
        return packageManager.getPackageInfo(packageName,0)!!.versionName
    }

    /**
     * 获取当前版本号
     * @return Int形式当前版本号
     * */
    fun getVersionNumer():Int{
        return packageManager.getPackageInfo(packageName,0)!!.versionCode
    }
    protected var IsUp:Boolean=false
    /**
     * 从网络获取最新版本号，判断
     * */
    override fun IsUpdate(versionCode: Int) {
        handler.sendEmptyMessage(UPDATA)
        IsUp=versionCode==getVersionNumer()
    }

    override fun onLodinData(product: Product<ResultX>) {
        handler.sendEmptyMessage(LODINDATA)
        DataUlit.product=product
    }

    private fun UpData(){
        if (!IsUp){
            var create = AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("优化bug,新增功能")
                .setTitle("下载最新版本")
                .setNegativeButton("取消", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        startActivity(Intent(this@HomeActivity,MainActivity::class.java))
                        finish()
                    }
                })
                .setPositiveButton("确定",object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                    }
                })
                .create()
            create.show()
        }
    }

    /***
     * @return初始化Presneter
     */
    override fun setPresneter(): HomePresenter=HomePresenter(HomeModle(), this)

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}