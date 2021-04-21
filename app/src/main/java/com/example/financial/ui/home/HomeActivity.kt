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
    /***
     * 常量
     * 网络请求
     * 判断是否需要更新
     * 倒计时
     */
    private companion object {
        val LODINDATA:Int=-10001
        val UPDATA:Int=-10002
        val TIME:Int=-10003
    }

    /***
     * handler 接收消息 每个任务完成时都会发送一条消息由handler处理，所有任务都完成以后弹出是否更新的弹窗
     * 是否完成网络请求
     * 是否获取最新版本并判断
     * 是否完成倒计时
     */
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
                if(IsUp){
                    startActivity(Intent(this@HomeActivity,MainActivity::class.java))
                    finish()
                }else{
                    UpData()
                }
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

    override fun initView() {}

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

    /**
     * 请求主页数据
     * @param 请求到数据类通过工具类存储
     */
    override fun onLodinData(product: Product<ResultX>) {
        handler.sendEmptyMessage(LODINDATA)
        DataUlit.product=product
    }

    /**
     * 询问弹窗如果不需要更新泽
     */
    private fun UpData(){
            var create = AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("优化bug,新增功能")
                .setTitle("下载最新版本")
                .setNegativeButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                    }
                })

                .setPositiveButton("取消",object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        startActivity(Intent(this@HomeActivity,MainActivity::class.java))
                        finish()
                    }
                })
                .create()
            create.show()
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