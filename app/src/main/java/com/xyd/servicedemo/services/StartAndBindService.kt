package com.xyd.servicedemo.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import com.blankj.utilcode.util.LogUtils


class StartAndBindService : Service() {

    val TAG = "StartAndBindService"
    override fun onBind(intent: Intent): IBinder? {
        if (Looper.getMainLooper() == Looper.myLooper()){
            LogUtils.i(TAG,"onBind运行在主线程")
        }else {
            LogUtils.i(TAG,"onBind运行在子线程")
        }
        return null
    }

    override fun onCreate() {
        super.onCreate()
        if (Looper.getMainLooper() == Looper.myLooper()){
            LogUtils.i(TAG,"onCreate运行在主线程")
        }else {
            LogUtils.i(TAG,"onCreate运行在子线程")
        }
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        if (Looper.getMainLooper() == Looper.myLooper()){
            LogUtils.i(TAG,"onStart运行在主线程")
        }else {
            LogUtils.i(TAG,"onStart运行在子线程")
        }
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Looper.getMainLooper() == Looper.myLooper()){
            LogUtils.i(TAG,"onStartCommand运行在主线程")
        }else {
            LogUtils.i(TAG,"onStartCommand运行在子线程")
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Looper.getMainLooper() == Looper.myLooper()){
            LogUtils.i(TAG,"onDestroy运行在主线程")
        }else {
            LogUtils.i(TAG,"onDestroy运行在子线程")
        }
    }
}