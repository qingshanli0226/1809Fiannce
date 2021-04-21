package com.example.financial.ui.welcome

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.view.WindowManager
import com.alibaba.android.arouter.launcher.ARouter
import com.example.financial.MainActivity
import com.example.financial.R
import com.example.financial.base.Index
import com.example.financial.base.UpData
import com.example.financial.ulit.DataUlit
import com.example.frame_library.mvp.BaseActitvty
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*
import kotlin.concurrent.timerTask

class WelcomeActivity : BaseActitvty<WelcomePresenter>(), WelcomeCanter.View {
    /**
     * 是否需要更新
     */
    protected var IsUp: Boolean = false

    /***
     * 常量
     * 网络请求
     * 判断是否需要更新
     * 倒计时
     */
    private companion object {
        val LODINDATA: Int = -10001
        val UPDATA: Int = -10002
        val TIME: Int = -10003
    }

    /***
     * handler 接收消息 每个任务完成时都会发送一条消息由handler处理，所有任务都完成以后弹出是否更新的弹窗
     * 是否完成网络请求
     * 是否获取最新版本并判断
     * 是否完成倒计时
     */
    private val handler: Handler = object : Handler() {

        private var ISLODINDATA: Boolean = false
        private var ISUPDATA: Boolean = false
        private var ISTIME: Boolean = false

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when (msg.what) {
                LODINDATA -> ISLODINDATA = true
                UPDATA -> ISUPDATA = true
                TIME -> ISTIME = true
            }

            if (ISLODINDATA && ISTIME && ISUPDATA) {
                if (IsUp) {
                    ARouter.getInstance().build(DataUlit.AROUTER_JUMP_MAIN).navigation()
                    finish()
                } else {
                    UpData()
                }
            }
        }
    }

    private val timer: Timer by lazy {
        Countdown()
    }

    override fun bandLayoutId(): Int {
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        return R.layout.activity_welcome
    }

    override fun initView() {}

    override fun initData() {
        act_welcome_version_text.setText(getVersionName() + "")

        mPresenter!!.getVersion()

        timer
    }

    /**
     *获取当前版本名称
     *  @return 以String形式返回版本名称
     */
    fun getVersionName(): String? {
        return packageManager.getPackageInfo(packageName, 0)!!.versionName
    }

    /**
     * 获取当前版本号
     * @return Int形式当前版本号
     * */
    fun getVersionNumer(): Int {
        return packageManager.getPackageInfo(packageName, 0)!!.versionCode
    }

    /**
     * 询问弹窗如果不需要更新泽
     */
    private fun UpData() {
        var create = AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_launcher_background)
            .setMessage("优化bug,新增功能")
            .setTitle("下载最新版本")
            .setNegativeButton("确定", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            })

            .setPositiveButton("取消", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    //ARouter.getInstance().build(DataUlit.AROUTER_JUMP_MAIN).withInt("",1).navigation()
                    startActivity(Intent(this@WelcomeActivity,MainActivity::class.java))
                    finish()
                }
            })
            .create()
        create.show()
    }

    /***
     * @return初始化Presneter
     */
    override fun setPresneter(): WelcomePresenter = WelcomePresenter(WelcomeModle(), this)

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    /**
     * 从网络获取最新版本号，判断
     * @param upData 网络请求获取的数据
     */
    override fun onUpdate(upData: UpData) {
        handler.sendEmptyMessage(UPDATA)
        IsUp = upData.versionCode == getVersionNumer()
    }

    /**
     * 请求主页数据
     * @param index 请求到数据类通过工具类存储
     */
    override fun onData(index: Index) {
        handler.sendEmptyMessage(LODINDATA)
        DataUlit.index = index
    }

    /**
     * 倒计时
     */
    private fun Countdown(): Timer {
        var time = 3
        var timer1 = Timer()
        timer1.schedule(timerTask {
            if (time== 0) {
                handler.sendEmptyMessage(TIME)
                cancel()
            } else {
                handler.post {
                    act_welcome_time_text.setText("${time}")
                }
                time--
            }

        }, 1000, 1000)
        return timer1
    }
}