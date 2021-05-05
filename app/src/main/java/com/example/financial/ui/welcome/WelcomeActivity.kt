package com.example.financial.ui.welcome

import android.app.AlertDialog
import android.content.ComponentName
import android.content.DialogInterface
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.KeyEvent
import android.view.WindowManager
import com.example.financial.ui.MainActivity
import com.example.financial.R
import com.example.financial.automatic.LoginServer
import com.example.financial.base.Index
import com.example.financial.base.UpData
import com.example.financial.ulit.DataUlit
import com.example.frame_library.mvp.BaseActitvty
import com.example.net_library.User
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.*
import kotlin.concurrent.timerTask

/**
 * 欢迎页实现
 * 1.判断是否需要更新应用，
 *  当需要更新时弹出是否更新的对话框
 * 2.请求首页数据
 * 3.欢迎动画或是倒计时
 * 4.当1。2。3.都完成后才能跳转到首页
 * 5。在开启的后台服务请求自动登录
 * 6.需要更新时在欢迎页开启的后台下载并在通知栏中显示进度
 * 7.销毁页面时注意内存泄露
 * 8.本页不能退出
 */
class WelcomeActivity : BaseActitvty<WelcomePresenter>(), IWelcomeCanter.View {
    /**
     * 是否需要更新
     */
    protected var IsUp: Boolean = false
    private var bind: LoginServer.Bind?=null
    private var updata:UpData?=null
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
     * @param ISLODINDATA 是否完成加载首页
     * @param ISUPDATA 是否获取最新最新版本并弹出是否更新的弹框
     * @param ISTIME 是否完成倒计时
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
//                if (IsUp) {
//
//                } else {
//                    //UpData()
//                }
            }
        }
    }

    /**
     * 将倒计时的timer对象提为全局便于销毁
     */
    //private val timer: Timer =starTimer(3)

    /**
     * @return 返回计数器对象
     */
    private fun starTimer(i:Int): Timer {
        var time=i
        var timer = Timer()
        timer.schedule(timerTask {
            if (time == 0) {
                handler.sendEmptyMessage(TIME)
                cancel()
            } else {
                handler.post {
                    act_welcome_time_text.setText("${time}")
                }
                time--
            }

        }, 1000, 1000)
        return timer
    }


    override fun bandLayoutId(): Int {
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏

        return R.layout.activity_welcome
    }

    override fun initView() {
        attaPresenter(WelcomePresenter(WelcomeModle(), this))
    }

    override fun initData() {
        act_welcome_version_text.setText(getVersionName() + "")

        mPresenter!!.getVersion()

        starTimer(3)

        starAutomaticLogin()
    }

    private fun starAutomaticLogin() {
        if (!User.long) {
            bindService(Intent(this, LoginServer::class.java), object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    bind = service as LoginServer.Bind
                    bind!!.getParent().starLogin()
                }

                override fun onServiceDisconnected(name: ComponentName?) {

                }
            }, BIND_AUTO_CREATE)
        }
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
     * 弹出对话框是否需要更新
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
                    startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                }
            })
            .create()
        create.show()
    }

    /**
     * 销毁计时器
     */
//    override fun onDestroy() {
//        super.onDestroy()
//        timer.cancel()
//    }

    /**
     * 从网络获取最新版本号，判断
     * @param upData 网络请求获取的数据
     */
    override fun onUpdate(upData: UpData) {
        this.updata=updata
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
     * 此页面禁止回退
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false
        }
        return super.onKeyDown(keyCode, event)
    }

}