package com.xyd.servicedemo

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.support.v7.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.xyd.servicedemo.services.BookService
import com.xyd.servicedemo.services.MovieService
import com.xyd.servicedemo.services.MusicService
import com.xyd.servicedemo.services.SEND_MESSAGE_CODE
import kotlinx.android.synthetic.main.activity_main.*


private val TAG = "MainActivity"


val RECEIVE_MESSAGE_CODE : Int  = 0x0002

//客户端的消息处理
class ClientHndler : Handler(){
    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        LogUtils.i(TAG,"clientHndler")
        when (msg?.what) {
            RECEIVE_MESSAGE_CODE -> {
                LogUtils.i(TAG,"这是来自服务端的问候：" + msg.obj)
            }
            else -> {
                LogUtils.i(TAG,"未接收到服务端的问候！！！")
            }
        }
    }
}

//初始化客户端的messenger
val clientMessenger = Messenger(ClientHndler())

class MainActivity : AppCompatActivity(),ServiceConnection{

    override fun onServiceDisconnected(p0: ComponentName?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        LogUtils.i(TAG,"onServiceDisconnected")
    }

    private lateinit var movieService: MovieService

    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        LogUtils.i(TAG,"onServiceConnected")
        var movieBinder = p1 as MovieService.MovieBinder
        movieService = movieBinder.getMovieService()
    }



    class Conn : ServiceConnection{
        val TAG = "Conn"
        override fun onServiceDisconnected(p0: ComponentName?) {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            LogUtils.i(TAG,"onServiceDisconnected")
        }

        lateinit var serviceMessenger: Messenger

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            LogUtils.i(TAG,"onServiceConnected")
            serviceMessenger = Messenger(p1)
        }

    }


    val conn = Conn()

    private var bookbinders = Intent()
    private var movieBinders = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogUtils.i(TAG,"onCreate")
        //直接启动service
        btn_start.setOnClickListener {
            bookbinders.setClass(this,BookService().javaClass)
            startService(bookbinders)
        }
        //停止service
        btn_stop.setOnClickListener {
            stopService(bookbinders)
        }

        //绑定service
        btn_bind.setOnClickListener {
            movieBinders.setClass(this,MovieService::class.java)
            bindService(movieBinders,this,Service.BIND_AUTO_CREATE)
        }
        //解除绑定
        btn_unbind.setOnClickListener {
            unbindService(this)
        }
        //获取数据
        btn_getdata.setOnClickListener {
            val count = movieService.movieName
            println("从service获取的count===$count")
        }
        //msg  bind
        btn_msg_bind.setOnClickListener {
            var  intent = Intent()
            intent.setClass(this@MainActivity,MusicService::class.java)
            bindService(intent,conn,Service.BIND_AUTO_CREATE)
        }
        //给service发送消息
        btn_send_msg.setOnClickListener {
            val message = Message.obtain(null, SEND_MESSAGE_CODE, 0, 0)
            message.obj = "来自客户端的问候！！！"
            message.replyTo = clientMessenger
            conn.serviceMessenger.send(message)
        }
        //取消绑定
        btn_msg_unbind.setOnClickListener {
            unbindService(conn)
        }
    }


    override fun onStart() {
        super.onStart()
        LogUtils.i(TAG,"onStart")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtils.i(TAG,"onRestart")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.i(TAG,"onResume")
    }
    override fun onPause() {
        super.onPause()
        LogUtils.i(TAG,"onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.i(TAG,"onDestroy")
    }


}
