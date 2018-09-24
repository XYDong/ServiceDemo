package com.xyd.servicedemo.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import com.blankj.utilcode.util.LogUtils
import com.xyd.servicedemo.RECEIVE_MESSAGE_CODE

/**
 * Created by zejian
 * Time 2016/10/3.
 * Description:Messenger服务端简单实例,服务端进程
 */

//此变量定义在类外部就可以被全包访问
val  SEND_MESSAGE_CODE : Int = 0x0001


//服务端的消息处理
class InComingHandler : Handler(){

    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        if (msg != null) {
            when (msg.what) {
                SEND_MESSAGE_CODE -> {
                    LogUtils.i("接受到客户端消息：" + msg.obj)
                    //接受完客户端的消息后需要返回给客户端消息
                    //通过msg.replyTo 方法 获取客户端的messenger
                    val clientMessenger = msg.replyTo
                    //获取到messenger之后，在通过客户端的messenger给客户端发送消息
                    val msg = Message.obtain()
                    msg.what = RECEIVE_MESSAGE_CODE
                    msg.obj = "hello client!!!"
                    Thread.sleep(3000)
                    clientMessenger.send(msg)
                }
                else -> {
                    LogUtils.i("未接受到客户端消息")
                }
            }
        }

    }
}
private val mMessenger = Messenger(InComingHandler())

class MusicService : Service(){


    override fun onBind(p0: Intent?): IBinder? {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return mMessenger.binder
    }

}