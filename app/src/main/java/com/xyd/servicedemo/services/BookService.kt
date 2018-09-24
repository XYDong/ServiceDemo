package com.xyd.servicedemo.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.blankj.utilcode.util.LogUtils

class BookService : Service() {

    val TAG = "BookService"
    override fun onBind(intent: Intent): IBinder? {
        LogUtils.i(TAG,"onBind")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.i(TAG,"onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtils.i(TAG,"onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.i(TAG,"onDestroy")
    }
}
